package com.app.estation.mappers;

import com.app.estation.dto.ReleveDto;
import com.app.estation.entity.Releve;

import java.util.List;
import java.util.stream.Collectors;

public class ReleveMapper {

    public static ReleveDto fromEntity(Releve entity){
        if (null == entity){
            return null;
        }
        ReleveDto dto = new ReleveDto();
        dto.setId_releve(entity.getId_releve());
        dto.setCompteur(entity.getCompteur());
        dto.setDate_releve(entity.getDate_releve());
        dto.setPompeUser(PompeUserMapper.fromEntity(entity.getPompeUser()));
        return dto;
    }

    public static Releve toEntity(ReleveDto dto){
        if (null == dto){
            return null;
        }
        Releve entity = new Releve();
        entity.setId_releve(dto.getId_releve());
        entity.setCompteur(dto.getCompteur());
        entity.setDate_releve(dto.getDate_releve());
        entity.setPompeUser(PompeUserMapper.toEntity(dto.getPompeUser()));
        return entity;
    }

    public static List<ReleveDto> fromEntityList(List<Releve> releves) {
        if (releves == null){
            return null;
        }
        return releves.stream().map(ReleveMapper::fromEntity).collect(Collectors.toList());
    }

    public static List<Releve> toEntityList(List<ReleveDto> releves) {
        if (releves == null){
            return null;
        }
        return releves.stream().map(ReleveMapper::toEntity).collect(Collectors.toList());
    }
}
