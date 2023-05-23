package com.app.estation.mappers;

import com.app.estation.dto.ProduitDto;
import com.app.estation.entity.Produit;
import java.util.List;
import java.util.stream.Collectors;

public class ProduitMapper {

    public static ProduitDto fromEntity(Produit entity){
        if (null == entity){
            return null;
        }
        ProduitDto dto = new ProduitDto();
        dto.setId_produit(entity.getId_produit());
        dto.setNom_produit(entity.getNom_produit());
        dto.setActions(ProduitActionMapper.fromEntityList(entity.getActions()));
        dto.setType(TypeProduitMapper.fromEntity(entity.getType()));
        dto.setService(ServicesMapper.fromEntityWithoutStations(entity.getId_service()));
        dto.setPrix_achat(entity.getPrix_achat());
        dto.setPrix_vente(entity.getPrix_vente());
        return dto;
    }

    public static Produit toEntity(ProduitDto dto){
        if (null == dto){
            return null;
        }
        Produit entity = new Produit();
        entity.setId_produit(dto.getId_produit());
        entity.setNom_produit(dto.getNom_produit());
        entity.setActions(ProduitActionMapper.toEntityList(dto.getActions()));
        entity.setType(TypeProduitMapper.toEntity(dto.getType()));
        entity.setId_service(ServicesMapper.toEntity(dto.getService()));
        entity.setPrix_achat(dto.getPrix_achat());
        entity.setPrix_vente(dto.getPrix_vente());
        return entity;
    }

    public static Produit toEntityWithoutServices(ProduitDto dto){
        if (null == dto){
            return null;
        }
        Produit entity = new Produit();
        entity.setId_produit(dto.getId_produit());
        entity.setNom_produit(dto.getNom_produit());
        entity.setActions(ProduitActionMapper.toEntityList(dto.getActions()));
        entity.setType(TypeProduitMapper.toEntity(dto.getType()));
        entity.setPrix_achat(dto.getPrix_achat());
        entity.setPrix_vente(dto.getPrix_vente());
        return entity;
    }


    public static List<ProduitDto> fromEntityList(List<Produit> entities){
        if (entities == null){
            return null;
        }
        return entities.stream().map(ProduitMapper::fromEntity).collect(Collectors.toList());
    }

    public static List<Produit> toEntityList(List<ProduitDto> dtos){
        if (dtos == null){
            return null;
        }
        return dtos.stream().map(ProduitMapper::toEntity).collect(Collectors.toList());
    }

}
