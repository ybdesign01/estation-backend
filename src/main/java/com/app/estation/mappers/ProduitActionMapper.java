package com.app.estation.mappers;
import com.app.estation.dto.ProduitActionDto;
import com.app.estation.entity.ProduitAction;

import java.util.List;

public class ProduitActionMapper {

    public static ProduitActionDto fromEntity(ProduitAction entity){
        if (null == entity){
            return null;
        }
        ProduitActionDto dto = new ProduitActionDto();
        dto.setId_action(entity.getId_action());
        dto.setAction(entity.getAction());
        dto.setDate_action(entity.getDate_action());
        dto.setProduit(ProduitMapper.fromEntityWithoutSubclasses(entity.getProduit()));
        dto.setFournisseur(entity.getFournisseur());
        dto.setQuantite(entity.getQuantite());
        return dto;
    }

    public static ProduitAction toEntity(ProduitActionDto dto){
        if (null == dto){
            return null;
        }
        ProduitAction entity = new ProduitAction();
        entity.setId_action(dto.getId_action());
        entity.setAction(dto.getAction());
        entity.setProduit(ProduitMapper.toEntity(dto.getProduit()));
        entity.setFournisseur(dto.getFournisseur());
        entity.setQuantite(dto.getQuantite());
        entity.setDate_action(dto.getDate_action());
        return entity;
    }




    public static List<ProduitActionDto> fromEntityList(List<ProduitAction> actions) {
        if (actions == null){
            return null;
        }
        return actions.stream().map(ProduitActionMapper::fromEntity).collect(java.util.stream.Collectors.toList());
    }

    public static List<ProduitAction> toEntityList(List<ProduitActionDto> dtos){
        if (dtos == null){
            return null;
        }
        return dtos.stream().map(ProduitActionMapper::toEntity).collect(java.util.stream.Collectors.toList());
    }
}
