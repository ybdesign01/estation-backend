package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.ApiRequestException;
import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.TypeProduitDto;
import com.app.estation.entity.TypeProduit;
import com.app.estation.mappers.TypeProduitMapper;
import com.app.estation.repository.TypeProduitRepository;
import com.app.estation.service.EServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class TypeProduitServiceImpl implements EServices<TypeProduitDto,TypeProduitDto> {

    @Autowired
    private TypeProduitRepository typeProduitRepository;


    @Override
    public TypeProduitDto add(TypeProduitDto dto) {
        TypeProduit typeProduit = TypeProduitMapper.toEntity(dto);
        typeProduitRepository.save(typeProduit);
        return TypeProduitMapper.fromEntity(typeProduitRepository.findById(typeProduit.getId_type()).orElseThrow(()-> new ApiRequestException("type_not_added")));
    }

    @Override
    public TypeProduitDto update(TypeProduitDto dto) {
        TypeProduit typeProduit = TypeProduitMapper.toEntity(dto);
        typeProduitRepository.save(typeProduit);
        return TypeProduitMapper.fromEntity(typeProduitRepository.findById(typeProduit.getId_type()).orElseThrow(()-> new ApiRequestException("type_not_updated")));
    }

    @Override
    public boolean delete(Long id) {
        TypeProduit typeProduit = typeProduitRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("type_not_found"));
        typeProduitRepository.deleteById(id);
        return typeProduitRepository.existsById(id);
    }

    @Override
    public TypeProduitDto get(Long id) {
        return TypeProduitMapper.fromEntity(typeProduitRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("type_not_found")));
    }

    @Override
    public List<TypeProduitDto> getAll() {
        List<TypeProduit> list = typeProduitRepository.findAll();
        if (list.isEmpty()){
            throw new EntityNotFoundException("no_type_found");
    }
        return TypeProduitMapper.fromEntityList(list);
    }
}
