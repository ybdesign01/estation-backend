package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.ApiRequestException;
import com.app.estation.dto.*;
import com.app.estation.entity.*;
import com.app.estation.mappers.CiterneMapper;
import com.app.estation.mappers.StationMapper;
import com.app.estation.repository.PompeUserRepository;
import com.app.estation.repository.ProduitActionRepository;
import com.app.estation.repository.ProduitRepository;
import com.app.estation.repository.TransactionRepository;
import com.app.estation.service.EServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class TransactionServiceImpl implements EServices<TransactionDto,TransactionDto> {

    @Autowired
    private PompeUserRepository pompeUserRepository;

    @Autowired
    private PompeUserServiceImpl pompeUserService;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ReleveServiceImpl releveService;

    @Autowired
    private ProduitActionRepository produitActionRepository;

    @Autowired
    private StationServiceImpl stationService;

    @Autowired
    private CiterneServiceImpl citerneService;

    @Override
    public TransactionDto add(TransactionDto request) {
        return null;
    }

    @Override
    public TransactionDto get(Long id) {
        return null;
    }

    @Override
    public TransactionDto update(TransactionDto request, Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<TransactionDto> getAll() {
        return null;
    }

    public boolean addEncaissementPompeUser(EncaissementRequest encaissementRequest){
        PompeUser pompeUser = pompeUserRepository.findById(encaissementRequest.getIdPompeUser()).orElseThrow(()-> new ApiRequestException("pompe_user_not_found"));
        Long compteur = releveService.getCompteurByIdPompeUser(encaissementRequest.getIdPompeUser());
        ProduitAction produitAction = new ProduitAction();
        Citerne citerne = pompeUserService.getCiterneByPompeUser(encaissementRequest.getIdPompeUser());
        citerne.setCapaciteActuelle(citerne.getCapaciteActuelle() - compteur);
        citerneService.update(CiterneMapper.fromEntity(citerne),citerne.getId_citerne());
        Produit p = pompeUserService.getProduitByPompeUser(encaissementRequest.getIdPompeUser());
        produitAction.setProduit(p);
        produitAction.setAction(TypeAction.ACTION_SORTIE);
        produitAction.setDate_action(LocalDateTime.now());
        produitAction.setQuantite(compteur.toString() +" "+ p.getType().getUnite());
        produitAction.setFournisseur(null);
        produitActionRepository.save(produitAction);
        for (EncaissementDto dto: encaissementRequest.getEncaissements()) {
            Transaction transaction = new Transaction();
            if (!pompeUser.getUser().getStations().isEmpty()){
                transaction.setStation(pompeUser.getUser().getStations().get(0).getStation());
            }else {
                transaction.setStation(null);
            }
            transaction.setNote(encaissementRequest.getNote());
            transaction.setTypeTransaction(TypeTransaction.ENCAISSEMENT);
            transaction.setDateTransaction(LocalDateTime.now());
            transaction.setMontant(dto.getMontant());
            transaction.setIdProduitAction(produitAction);
            transaction.setTypePaiement(dto.getTypePaiement());
            transaction.setIdPompeUser(pompeUser);
            transactionRepository.save(transaction);
        }
        Long count = transactionRepository.findTransactionsByIdPompeUsers(List.of(encaissementRequest.getIdPompeUser()));
        System.out.println("count is " + count);
        if (count == encaissementRequest.getEncaissements().size()){
            return true;
        }else {
            return false;
        }
    }

    public boolean addEncaissementProduit(EncaissementProduitRequest encaissementRequest){
        Produit produit = produitRepository.findById(encaissementRequest.getIdProduit()).orElseThrow(()-> new ApiRequestException("produit_not_found"));
        ProduitAction produitAction = new ProduitAction();
        produitAction.setProduit(produit);
        produitAction.setAction(TypeAction.ACTION_SORTIE);
        produitAction.setDate_action(LocalDateTime.now());
        produitAction.setQuantite(encaissementRequest.getQuantite() +" "+ produit.getType().getUnite());
        produitAction.setFournisseur(null);
        produitActionRepository.save(produitAction);
        for (EncaissementDto dto: encaissementRequest.getEncaissements()) {
            Transaction transaction = new Transaction();
            transaction.setStation(null);
            transaction.setNote(encaissementRequest.getNote());
            transaction.setTypeTransaction(TypeTransaction.ENCAISSEMENT);
            transaction.setDateTransaction(LocalDateTime.now());
            transaction.setMontant(dto.getMontant());
            transaction.setIdProduitAction(produitAction);
            transaction.setTypePaiement(dto.getTypePaiement());
            transaction.setIdPompeUser(null);
            transactionRepository.save(transaction);
        }
        Long count = transactionRepository.findTransactionsByIdProduits(List.of(encaissementRequest.getIdProduit()));
        if (count == encaissementRequest.getEncaissements().size()){
            return true;
        }else {
           return false;
        }
    }

    public boolean debitTransaction(DebitRequest debitRequest){
        Station station = StationMapper.toEntity(stationService.get(debitRequest.getStationId()));
        Transaction transaction = new Transaction();
        transaction.setStation(station);
        transaction.setNote(debitRequest.getNote());
        transaction.setTypeTransaction(TypeTransaction.DEBIT);
        transaction.setDateTransaction(LocalDateTime.now());
        transaction.setMontant(debitRequest.getMontant());
        transaction.setIdProduitAction(null);
        transaction.setTypePaiement(null);
        transaction.setIdPompeUser(null);
        transactionRepository.save(transaction);
        return transactionRepository.existsById(transaction.getIdTransaction());
    }



}
