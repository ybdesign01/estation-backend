package com.app.estation.service.implementation;

import com.app.estation.dto.TypeProduitDto;
import com.app.estation.entity.TypeProduit;
import com.app.estation.mappers.TypeProduitMapper;
import com.app.estation.repository.TypeProduitRepository;
import com.app.estation.service.TypeProduitService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class TypeProduitServiceImpl implements TypeProduitService {

    @Autowired
    private TypeProduitRepository typeProduitRepository;


    @Override
    public TypeProduitDto addTypeProduit(TypeProduitDto dto) {
        TypeProduit typeProduit = TypeProduitMapper.toEntity(dto);
        typeProduitRepository.save(typeProduit);
        return TypeProduitMapper.fromEntity(typeProduitRepository.findById(typeProduit.getId_type()).orElse(null));
    }

    @Override
    public TypeProduitDto updateTypeProduit(TypeProduitDto dto) {
        TypeProduit typeProduit = TypeProduitMapper.toEntity(dto);
        typeProduitRepository.save(typeProduit);
        return TypeProduitMapper.fromEntity(typeProduitRepository.findById(typeProduit.getId_type()).orElse(null));
    }

    @Override
    public TypeProduitDto deleteTypeProduit(Long id) {
        return null;
    }

    @Override
    public TypeProduitDto getTypeProduit(Long id) {
        return TypeProduitMapper.fromEntity(typeProduitRepository.findById(id).orElse(null));
    }

    @Override
    public List<TypeProduitDto> getAllTypeProduits() {
        return TypeProduitMapper.fromEntityList(typeProduitRepository.findAll());
    }
}
