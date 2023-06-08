package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.ApiRequestException;
import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.ProduitActionDto;
import com.app.estation.dto.ProduitDto;
import com.app.estation.entity.HistoriquePrix;
import com.app.estation.entity.Produit;
import com.app.estation.entity.ProduitAction;
import com.app.estation.mappers.ProduitActionMapper;
import com.app.estation.mappers.ProduitMapper;
import com.app.estation.repository.*;
import com.app.estation.service.EServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ProduitServiceImpl implements EServices<ProduitDto, ProduitDto> {

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private TypeProduitRepository typeProduitRepository;

    @Autowired
    private HistoriquePrixRepository historiquePrixRepository;

    @Autowired
    private HistoriquePrixServiceImpl historiquePrixServiceImpl;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ProduitActionRepository produitActionRepository;


    @Override
    public ProduitDto add(ProduitDto dto) {
        if (!typeProduitRepository.existsById(dto.getType().getId_type())){
            throw new ApiRequestException("type_does_not_exist");
        }
        if (!serviceRepository.existsById(dto.getService().getId())){
            throw new ApiRequestException("service_does_not_exist");
        }
        Produit produit = ProduitMapper.toEntity(dto);
        produitRepository.save(produit);

        HistoriquePrix historiquePrix = new HistoriquePrix();
        historiquePrix.setPrixAchat(dto.getPrix_achat());
        historiquePrix.setPrixVente(dto.getPrix_vente());
        historiquePrix.setDateDebut(LocalDateTime.now());
        historiquePrix.setIdProduit(produit);
        historiquePrixRepository.save(historiquePrix);
        return ProduitMapper.fromEntity(produitRepository.findById(produit.getId_produit()).orElseThrow(()-> new ApiRequestException("produit_not_added")));
    }

    @Override
    public ProduitDto update(ProduitDto dto, Long id) {
        if (!produitRepository.existsById(id)){
            throw new EntityNotFoundException("produit_not_found");
        }
        if (!typeProduitRepository.existsById(dto.getType().getId_type())){
            throw new EntityNotFoundException("type_not_found");
        }
        Produit produit = ProduitMapper.toEntity(dto);
        produit.setId_produit(id);
        boolean isPriceUpdated = historiquePrixServiceImpl.updateAndAdd(produit);
        produitRepository.save(produit);
        return ProduitMapper.fromEntity(produitRepository.findById(produit.getId_produit()).orElseThrow(()-> new ApiRequestException("produit_not_updated")));
    }

    @Override
    public boolean delete(Long id) {
        Produit produit = produitRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("produit_not_found"));
        produitRepository.deleteById(id);
        return produitRepository.existsById(id);
    }

    @Override
    public ProduitDto get(Long id) {
        return ProduitMapper.fromEntity(produitRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("produit_not_found")));
    }


    @Override
    public List<ProduitDto> getAll() {
        List<Produit> list = produitRepository.findAll();
        if (list.isEmpty()){
            throw new EntityNotFoundException("no_produit_found");
        }
        return ProduitMapper.fromEntityList(list);
    }

    public List<ProduitDto> getByStation(Long id) {
        List<Produit> list = produitRepository.findByStation(id);
        System.out.println(list);
        if (list.isEmpty()){
            throw new EntityNotFoundException("no_produit_found");
        }
        return ProduitMapper.fromEntityList(list);
    }

    public List<ProduitActionDto> getActions(Long id) {
        return ProduitActionMapper.fromEntityListWithoutSubclasses(produitActionRepository.getByProduitId(id));
    }

    public Boolean addAction(ProduitActionDto produitDto) {
        Produit produit = produitRepository.findById(produitDto.getProduit().getId_produit()).orElseThrow(()-> new EntityNotFoundException("produit_not_found"));
        ProduitAction produitAction = ProduitActionMapper.toEntity(produitDto);
        produitAction.setId_action(null);
        produitAction.setProduit(produit);
        produitAction.setDate_action(LocalDateTime.now());
        produitActionRepository.save(produitAction);

        return produitActionRepository.existsById(produitAction.getId_action());
    }
}
