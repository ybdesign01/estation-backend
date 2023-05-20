package com.app.estation.mappers;

import com.app.estation.dto.PompeDto;
import com.app.estation.entity.Pompe;

import java.util.List;
import java.util.stream.Collectors;

public class PompeMapper {

    public static Pompe toEntity(PompeDto dto){
        if (dto == null) {
            return null;
        }
        Pompe entity = new Pompe();
        entity.setId_pompe(dto.getId_pompe());
        entity.setNom_pompe(dto.getNom_pompe());
        entity.setUsers(PompeUserMapper.toEntityList(dto.getUsers()));
        entity.setCiternes(CiterneMapper.toEntityList(dto.getCiternes()));
        return entity;
    }

    public static Pompe toEntityWithoutSubclasses(PompeDto dto){
        if (dto == null) {
            return null;
        }
        Pompe entity = new Pompe();
        entity.setId_pompe(dto.getId_pompe());
        entity.setNom_pompe(dto.getNom_pompe());
        entity.setCiternes(CiterneMapper.toEntityList(dto.getCiternes()));
        return entity;
    }

    public static PompeDto fromEntity(Pompe entity){
        if (entity == null){
            return null;
        }
        PompeDto dto = new PompeDto();
        dto.setId_pompe(entity.getId_pompe());
        dto.setNom_pompe(entity.getNom_pompe());
        dto.setCiternes(CiterneMapper.fromEntityList(entity.getCiternes()));
        dto.setUsers(PompeUserMapper.fromEntityList(entity.getUsers()));
        return dto;
    }

    public static PompeDto fromEntityWithoutSubclasses(Pompe entity){
        if (entity == null){
            return null;
        }
        PompeDto dto = new PompeDto();
        dto.setId_pompe(entity.getId_pompe());
        dto.setNom_pompe(entity.getNom_pompe());
        return dto;
    }


    public static List<PompeDto> fromEntityList(List<Pompe> entities){
        if (entities == null){
            return null;
        }
        List<PompeDto> dtos = entities.stream().map(PompeMapper::fromEntity).collect(Collectors.toList());
        return dtos;
    }

    public static List<Pompe> toEntityList(List<PompeDto> dtos){
        if (dtos == null){
            return null;
        }
        List<Pompe> entities = dtos.stream().map(PompeMapper::toEntity).collect(Collectors.toList());
        return entities;
    }


    public static List<PompeDto> fromEntityListWithoutSubclasses(List<Pompe> entities) {
        if (entities == null){
            return null;
        }
        List<PompeDto> dtos = entities.stream().map(PompeMapper::fromEntityWithoutSubclasses).collect(Collectors.toList());
        return dtos;
    }
}
