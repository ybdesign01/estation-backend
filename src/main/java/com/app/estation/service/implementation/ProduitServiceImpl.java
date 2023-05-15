package com.app.estation.service.implementation;

import com.app.estation.dto.ProduitDto;
import com.app.estation.repository.ProduitRepository;
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


    @Override
    public ProduitDto addProduit(ProduitDto dto) {
        return null;
    }

    @Override
    public ProduitDto updateProduit(ProduitDto dto) {
        return null;
    }

    @Override
    public ProduitDto deleteProduit(Long id) {
        return null;
    }

    @Override
    public ProduitDto getProduit(Long id) {
        return null;
    }

    @Override
    public List<ProduitDto> getAllProduits() {
        return null;
    }
}
