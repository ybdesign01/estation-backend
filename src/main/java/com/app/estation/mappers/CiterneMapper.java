package com.app.estation.mappers;

import com.app.estation.dto.CiterneDto;
import com.app.estation.entity.Citerne;

import java.util.List;

public class CiterneMapper {

    public static CiterneDto fromEntity(Citerne entity){
        if (null == entity){
            return null;
        }
        CiterneDto dto = new CiterneDto();
        dto.setId_citerne(entity.getId_citerne());
        dto.setNom_citerne(entity.getNom_citerne());
        dto.setCapacite(entity.getCapacite());
        dto.setPompes(PompeMapper.fromEntityList(entity.getPompes()));
        dto.setProduit(ProduitMapper.fromEntity(entity.getId_produit()));
        return dto;
    }

    public static Citerne toEntity(CiterneDto dto){
        if (null == dto){
            return null;
        }
        Citerne entity = new Citerne();
        entity.setId_citerne(dto.getId_citerne());
        entity.setNom_citerne(dto.getNom_citerne());
        entity.setCapacite(dto.getCapacite());
        entity.setPompes(PompeMapper.toEntityList(dto.getPompes()));
        entity.setId_produit(ProduitMapper.toEntityWithoutServices(dto.getProduit()));
        return entity;
    }

    public static List<CiterneDto> fromEntityList(List<Citerne> citerne) {
        if (citerne == null){
            return null;
        }
        return citerne.stream().map(CiterneMapper::fromEntity).collect(java.util.stream.Collectors.toList());
    }

    public static List<Citerne> toEntityList(List<CiterneDto> citerne) {
        if (citerne == null){
            return null;
        }
        return citerne.stream().map(CiterneMapper::toEntity).collect(java.util.stream.Collectors.toList());
    }

}
