package com.app.estation.service;

import com.app.estation.dto.ProduitDto;

import java.util.List;

public interface ProduitService {

    ProduitDto addProduit(ProduitDto dto);
    ProduitDto updateProduit(ProduitDto dto);

    ProduitDto deleteProduit(Long id);

    ProduitDto getProduit(Long id);

    List<ProduitDto> getAllProduits();


}
