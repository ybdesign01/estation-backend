package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.ApiRequestException;
import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.*;
import com.app.estation.entity.*;
import com.app.estation.mappers.CiterneMapper;
import com.app.estation.mappers.StationMapper;
import com.app.estation.mappers.TransactionGroupMapper;
import com.app.estation.mappers.TransactionMapper;
import com.app.estation.repository.*;
import com.app.estation.service.EServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Math.abs;

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
    private TransactionGroupRepository transactionGroupRepository;

    @Autowired
    private ReleveServiceImpl releveService;

    @Autowired
    private ProduitActionRepository produitActionRepository;

    @Autowired
    private StationServiceImpl stationService;

    @Autowired
    private CiterneServiceImpl citerneService;

    @Autowired
    private TypePaiementRepository typePaiementRepository;

    @Override
    public TransactionDto add(TransactionDto request) {
        return null;
    }

    @Override
    public TransactionDto get(Long id) {
        return null;
    }

    public TransactionGroupDto getTransaction(Long id) {
        return TransactionGroupMapper.fromEntity(transactionGroupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("transaction_not_found")));
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
        List<Transaction> transactions = transactionRepository.findAll();
        if (transactions.isEmpty()){
            throw new EntityNotFoundException("no_transactions_found");
        }
        return TransactionMapper.fromEntityList(transactions);
    }

    public List<TransactionGroupDto> getAllGroups() {
        List<TransactionGroup> transactions = transactionGroupRepository.findAll();
        if (transactions.isEmpty()){
            throw new EntityNotFoundException("no_transactions_found");
        }
        return TransactionGroupMapper.fromEntityList(transactions);
    }

    public Map<String, List<TransactionGroupDto>> getAllTransactions(){
        final List<TransactionGroup> encaissements = transactionGroupRepository.findTransactionGroupsByTypeTransaction(TypeTransaction.ENCAISSEMENT);
        final List<TransactionGroup> debits = transactionGroupRepository.findTransactionGroupsByTypeTransaction(TypeTransaction.DEBIT);
        final Map<String, List<TransactionGroupDto>> map = Map.of("encaissements", TransactionGroupMapper.fromEntityList(encaissements), "debits", TransactionGroupMapper.fromEntityList(debits));
        if (encaissements.isEmpty() && debits.isEmpty()){
            throw new EntityNotFoundException("no_transactions_found");
        }
        return map;
    }

    public boolean addEncaissementPompeUser(EncaissementRequest encaissementRequest){
        PompeUser pompeUser = pompeUserRepository.findById(encaissementRequest.getIdPompeUser()).orElseThrow(()-> new ApiRequestException("pompe_user_not_found"));
        System.out.println("id is " + pompeUser.getIdPompeUser());
        Long count1 = transactionGroupRepository.findTransactionsByIdPompeUsers(List.of(encaissementRequest.getIdPompeUser()));
        if (count1 > 0){
            throw new ApiRequestException("pompe_user_already_encaissed");
        }
        double compteur = releveService.getCompteurByIdPompeUser(encaissementRequest.getIdPompeUser()).doubleValue();

        ProduitAction produitAction = new ProduitAction();
        Citerne citerne = pompeUserService.getCiterneByPompeUser(encaissementRequest.getIdPompeUser());
        if (citerne == null){
            throw new EntityNotFoundException("citerne_not_found");
        }
        if (citerne.getCapaciteActuelle() - (compteur/1000) < 0){
            throw new ApiRequestException("citerne_capacity_exceeded");
        }
        citerne.setCapaciteActuelle(citerne.getCapaciteActuelle() - (compteur/1000));
        citerneService.update(CiterneMapper.fromEntity(citerne),citerne.getId_citerne());
        Produit p = pompeUserService.getProduitByPompeUser(encaissementRequest.getIdPompeUser());
        produitAction.setProduit(p);
        produitAction.setAction(TypeAction.ACTION_SORTIE);
        produitAction.setDate_action(LocalDateTime.now());
        produitAction.setQuantite((compteur/1000) +" "+ p.getType().getUnite());
        System.out.println((compteur/1000) +" "+ p.getType().getUnite());
        produitAction.setFournisseur(null);
        produitActionRepository.save(produitAction);

        TransactionGroup transactionGroup = new TransactionGroup();
        transactionGroup.setTypeTransaction(TypeTransaction.ENCAISSEMENT);
        transactionGroup.setStation(p.getId_service().getStation());
        transactionGroup.setNote(encaissementRequest.getNote());
        transactionGroup.setDateTransaction(LocalDateTime.now());
        transactionGroup.setIdPompeUser(pompeUser);
        Double montantTotal = releveService.calculatePrice(pompeUser.getIdPompeUser());
        transactionGroup.setMontantTotal(montantTotal);
        AtomicReference<Double> montant = new AtomicReference<>(0.0);
        encaissementRequest.getEncaissements().forEach(encaissementDto -> {
            montant.updateAndGet(v -> v + encaissementDto.getMontant());
        });
        Double montantT = transactionGroupRepository.sumMontantPayeByUser(pompeUser.getUser().getId_user());
        if (null == montantT){
            montantT = 0.0;
        }
        transactionGroup.setMontantPaye(montant.get());
        if (montant.get() > montantTotal){
            montantT = montantT + abs(montantTotal - montant.get());
            transactionGroup.setMontantRestant(montantT);
        }else{
            montantT = montantT + (montantTotal - montant.get());
            transactionGroup.setMontantRestant(-montantT);
        }
        transactionGroup.setIdProduitAction(produitAction);
        transactionGroupRepository.save(transactionGroup);

        for (EncaissementDto dto: encaissementRequest.getEncaissements()) {
            Transaction transaction = new Transaction();
            transaction.setDateTransaction(LocalDateTime.now());
            transaction.setMontant(dto.getMontant());
            transaction.setTypePaiement(typePaiementRepository.findById(dto.getTypePaiement().getIdTypePaiement()).orElseThrow(()-> new EntityNotFoundException("type_paiement_not_found")));
            transaction.setIdTransactionGroup(transactionGroup);
            transactionRepository.save(transaction);
        }
        return transactionGroupRepository.existsById(transactionGroup.getIdTransactionGroup());
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

        TransactionGroup transactionGroup = new TransactionGroup();
        transactionGroup.setTypeTransaction(TypeTransaction.ENCAISSEMENT);
        transactionGroup.setStation(produit.getId_service().getStation());
        transactionGroup.setNote(encaissementRequest.getNote());
        transactionGroup.setDateTransaction(LocalDateTime.now());
        transactionGroup.setIdProduitAction(produitAction);
        transactionGroup.setMontantTotal(produit.getPrix_vente() * encaissementRequest.getQuantite());
        AtomicReference<Double> montant = new AtomicReference<>(0.0);
        encaissementRequest.getEncaissements().forEach(encaissementDto -> {
            montant.updateAndGet(v -> v + encaissementDto.getMontant());
        });
        transactionGroup.setMontantPaye(montant.get());
        transactionGroup.setMontantRestant(abs(transactionGroup.getMontantTotal() - montant.get()));
        transactionGroup.setIdPompeUser(null);

        transactionGroupRepository.save(transactionGroup);
        for (EncaissementDto dto: encaissementRequest.getEncaissements()) {
            Transaction transaction = new Transaction();
            transaction.setDateTransaction(LocalDateTime.now());
            transaction.setMontant(dto.getMontant());
            transaction.setTypePaiement(typePaiementRepository.findById(dto.getTypePaiement().getIdTypePaiement()).orElseThrow(()-> new EntityNotFoundException("type_paiement_not_found")));
            transaction.setIdTransactionGroup(transactionGroup);
            transactionRepository.save(transaction);
        }

        Long count = transactionGroupRepository.findTransactionsByIdProduits(List.of(encaissementRequest.getIdProduit()));
        System.out.println("count is " + count);
        return true;
    }

    public boolean debitTransaction(DebitRequest debitRequest){
        Station station = StationMapper.toEntity(stationService.get(debitRequest.getStationId()));
        TransactionGroup transactionGroup = new TransactionGroup();
        transactionGroup.setTypeTransaction(TypeTransaction.DEBIT);
        transactionGroup.setStation(station);
        transactionGroup.setNote(debitRequest.getNote());
        transactionGroup.setDateTransaction(LocalDateTime.now());
        transactionGroup.setIdProduitAction(null);
        transactionGroup.setMontantTotal(debitRequest.getMontant());
        transactionGroup.setMontantPaye(0.0);
        transactionGroup.setMontantRestant(0.0);
        transactionGroup.setIdPompeUser(null);
        transactionGroupRepository.save(transactionGroup);
        Transaction transaction = new Transaction();
        transaction.setIdTransactionGroup(transactionGroup);
        transaction.setDateTransaction(LocalDateTime.now());
        transaction.setMontant(debitRequest.getMontant());
        transaction.setTypePaiement(null);
        transactionRepository.save(transaction);
        return transactionRepository.existsById(transaction.getIdTransaction());
    }


    public List<TransactionGroupDto> getTransactionsByStation(Long id) {
        StationDto station = stationService.get(id);
        List<TransactionGroup> transactionGroups = transactionGroupRepository.findTransactionGroupsByStation(id);
        return TransactionGroupMapper.fromEntityList(transactionGroups);
    }

    public List<TransactionGroupDto> getEncaissements() {
        return TransactionGroupMapper.fromEntityList(transactionGroupRepository.findTransactionGroupsByTypeTransaction( TypeTransaction.ENCAISSEMENT));
    }

    public List<TransactionGroupDto> getDebits() {
        return TransactionGroupMapper.fromEntityList(transactionGroupRepository.findTransactionGroupsByTypeTransaction(TypeTransaction.DEBIT));
    }
}
