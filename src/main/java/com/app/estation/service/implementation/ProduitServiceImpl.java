package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.ApiRequestException;
import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.ProduitDto;
import com.app.estation.entity.Produit;
import com.app.estation.mappers.ProduitMapper;
import com.app.estation.repository.ProduitRepository;
import com.app.estation.repository.TypeProduitRepository;
import com.app.estation.service.EServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProduitServiceImpl implements EServices<ProduitDto, ProduitDto> {

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private TypeProduitRepository typeProduitRepository;


    @Override
    public ProduitDto add(ProduitDto dto) {
        if (!typeProduitRepository.existsById(dto.getType().getId_type())){
            throw new ApiRequestException("type_does_not_exist");
        }
        System.out.println(dto.getService());
        Produit produit = ProduitMapper.toEntity(dto);

        produitRepository.save(produit);
        return ProduitMapper.fromEntity(produitRepository.findById(produit.getId_produit()).orElseThrow(()-> new ApiRequestException("produit_not_added")));
    }

    @Override
    public ProduitDto update(ProduitDto dto) {
        Produit produit = ProduitMapper.toEntity(dto);
        produitRepository.save(produit);
        return ProduitMapper.fromEntity(produitRepository.findById(produit.getId_produit()).orElseThrow(()-> new ApiRequestException("produit_not_updated")));
    }

    @Override
    public boolean delete(Long id) {
        Produit produit = produitRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("produit_not_found"));
        produitRepository.deleteById(id);
        return true;
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
}
