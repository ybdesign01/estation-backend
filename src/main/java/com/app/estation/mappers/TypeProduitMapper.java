package com.app.estation.mappers;
import com.app.estation.dto.TypeProduitDto;
import com.app.estation.entity.TypeProduit;

import java.util.List;

public class TypeProduitMapper {

    public static TypeProduitDto fromEntity(TypeProduit entity) {
        if (entity == null) {
            return null;
        }
        return new TypeProduitDto(
            entity.getId_type(),
            entity.getUnite(),
            entity.getNom_type()
        );
    }

    public static TypeProduit toEntity(TypeProduitDto dto) {
        if (dto == null) {
            return null;
        }
        TypeProduit entity = new TypeProduit();
        entity.setId_type(dto.getId_type());
        entity.setUnite(dto.getUnite());
        entity.setNom_type(dto.getNom_type());
        return entity;
    }

    public static List<TypeProduitDto> fromEntityList(List<TypeProduit> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream().map(TypeProduitMapper::fromEntity).collect(java.util.stream.Collectors.toList());
    }

    public static List<TypeProduit> toEntityList(List<TypeProduitDto> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream().map(TypeProduitMapper::toEntity).collect(java.util.stream.Collectors.toList());
    }

}
