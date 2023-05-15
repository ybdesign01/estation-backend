package com.app.estation.service;

import com.app.estation.dto.TypeProduitDto;
import com.app.estation.entity.TypeProduit;

import java.util.List;

public interface TypeProduitService {

    TypeProduitDto addTypeProduit(TypeProduitDto dto);
    TypeProduitDto updateTypeProduit(TypeProduitDto dto);
    TypeProduitDto deleteTypeProduit(Long id);
    TypeProduitDto getTypeProduit(Long id);
    List<TypeProduitDto> getAllTypeProduits();
}
