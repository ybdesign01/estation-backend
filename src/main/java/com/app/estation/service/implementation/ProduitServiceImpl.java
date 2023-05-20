package com.app.estation.service.implementation;

import com.app.estation.advice.ApiRequestException;
import com.app.estation.dto.ProduitDto;
import com.app.estation.entity.Produit;
import com.app.estation.mappers.ProduitMapper;
import com.app.estation.repository.ProduitRepository;
import com.app.estation.repository.TypeProduitRepository;
import com.app.estation.service.ProduitService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private TypeProduitRepository typeProduitRepository;


    @Override
    public ProduitDto addProduit(ProduitDto dto) {
        if (!typeProduitRepository.existsById(dto.getType().getId_type())){
            throw new ApiRequestException("type_does_not_exist");
        }
        System.out.println(dto.getService());
        Produit produit = ProduitMapper.toEntity(dto);

        produitRepository.save(produit);
        return ProduitMapper.fromEntity(produitRepository.findById(produit.getId_produit()).orElse(null));
    }

    @Override
    public ProduitDto updateProduit(ProduitDto dto) {
        Produit produit = ProduitMapper.toEntity(dto);
        produitRepository.save(produit);
        return ProduitMapper.fromEntity(produitRepository.findById(produit.getId_produit()).orElse(null));
    }

    @Override
    public ProduitDto deleteProduit(Long id) {
        Produit produit = produitRepository.findById(id).orElse(null);
        produitRepository.deleteById(id);
        return ProduitMapper.fromEntity(produit);
    }

    @Override
    public ProduitDto getProduit(Long id) {
        return ProduitMapper.fromEntity(produitRepository.findById(id).orElse(null));
    }

    @Override
    public List<ProduitDto> getAllProduits() {
        return ProduitMapper.fromEntityList(produitRepository.findAll());
    }
}
