package com.app.estation.mappers;

import com.app.estation.dto.HistoriquePrixDto;
import com.app.estation.entity.HistoriquePrix;

import java.util.List;

public class HistoriquePrixMapper {

    public static HistoriquePrix toEntity(HistoriquePrixDto dto) {
        if (dto == null) {
            return null;
        }
        HistoriquePrix entity = new HistoriquePrix();
        entity.setIdHistoriquePrix(dto.getIdHistoriquePrix());
        entity.setPrixAchat(dto.getPrixAchat());
        entity.setPrixVente(dto.getPrixVente());
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());
        entity.setIdProduit(ProduitMapper.toEntity(dto.getIdProduit()));
        return entity;
    }

    public static HistoriquePrixDto fromEntity(HistoriquePrix entity) {
        if (entity == null) {
            return null;
        }
        HistoriquePrixDto dto = new HistoriquePrixDto();
        dto.setIdHistoriquePrix(entity.getIdHistoriquePrix());
        dto.setPrixAchat(entity.getPrixAchat());
        dto.setPrixVente(entity.getPrixVente());
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        dto.setIdProduit(ProduitMapper.fromEntity(entity.getIdProduit()));
        return dto;
    }

    public static List<HistoriquePrixDto> fromEntityList(List<HistoriquePrix> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream().map(HistoriquePrixMapper::fromEntity).collect(java.util.stream.Collectors.toList());
    }

    public static List<HistoriquePrix> toEntityList(List<HistoriquePrixDto> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream().map(HistoriquePrixMapper::toEntity).collect(java.util.stream.Collectors.toList());
    }
}
