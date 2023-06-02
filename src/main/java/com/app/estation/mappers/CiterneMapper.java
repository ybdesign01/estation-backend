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
        dto.setCapaciteMaximale(entity.getCapaciteMaximale());
        dto.setCapaciteActuelle(entity.getCapaciteActuelle());
        dto.setStation(StationMapper.fromEntityWithoutServices(entity.getStation()));
        dto.setPompes(CiternePompeMapper.fromEntityListWithoutCiterne(entity.getPompes()));
        dto.setProduit(ProduitMapper.fromEntityWithoutSubclasses(entity.getId_produit()));
        return dto;
    }

    public static Citerne toEntity(CiterneDto dto){
        if (null == dto){
            return null;
        }
        Citerne entity = new Citerne();
        entity.setId_citerne(dto.getId_citerne());
        entity.setNom_citerne(dto.getNom_citerne());
        entity.setStation(StationMapper.toEntityWithoutSubclasses(dto.getStation()));
        entity.setCapaciteMaximale(dto.getCapaciteMaximale());
        entity.setCapaciteActuelle(dto.getCapaciteActuelle());
        entity.setPompes(CiternePompeMapper.toEntityList(dto.getPompes()));
        entity.setId_produit(ProduitMapper.toEntity(dto.getProduit()));
        return entity;
    }

    public static Citerne toEntityWithoutSubclasses(CiterneDto dto){
        if (null == dto){
            return null;
        }
        Citerne entity = new Citerne();
        entity.setId_citerne(dto.getId_citerne());
        entity.setNom_citerne(dto.getNom_citerne());
        entity.setStation(StationMapper.toEntityWithoutSubclasses(dto.getStation()));
        entity.setCapaciteMaximale(dto.getCapaciteMaximale());
        entity.setCapaciteActuelle(dto.getCapaciteActuelle());
        entity.setId_produit(ProduitMapper.toEntity(dto.getProduit()));
        return entity;
    }

    public static CiterneDto fromEntityWithoutSubclasses(Citerne entity){
        if (null == entity){
            return null;
        }
        CiterneDto dto = new CiterneDto();
        dto.setId_citerne(entity.getId_citerne());
        dto.setNom_citerne(entity.getNom_citerne());
        dto.setStation(StationMapper.fromEntityWithoutServices(entity.getStation()));
        dto.setCapaciteMaximale(entity.getCapaciteMaximale());
        dto.setCapaciteActuelle(entity.getCapaciteActuelle());
        dto.setProduit(ProduitMapper.fromEntityWithoutSubclasses(entity.getId_produit()));
        return dto;
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

    public static List<Citerne> toEntityListWithoutSubclasses(List<CiterneDto> citerne) {
        if (citerne == null){
            return null;
        }
        return citerne.stream().map(CiterneMapper::toEntityWithoutSubclasses).collect(java.util.stream.Collectors.toList());
    }

    public static List<CiterneDto> fromEntityListWithoutSubclasses(List<Citerne> citerne) {
        if (citerne == null){
            return null;
        }
        return citerne.stream().map(CiterneMapper::fromEntityWithoutSubclasses).collect(java.util.stream.Collectors.toList());
    }

}
