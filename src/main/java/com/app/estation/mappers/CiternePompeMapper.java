package com.app.estation.mappers;

import com.app.estation.dto.CiternePompeDto;
import com.app.estation.entity.CiternePompe;

import java.util.List;

public class CiternePompeMapper {

    public static CiternePompeDto fromEntity(CiternePompe entity){
        if (null == entity){
            return null;
        }
        CiternePompeDto dto = new CiternePompeDto();
        dto.setIdCiternePompe(entity.getIdCiternePompe());
        dto.setPompe(PompeMapper.fromEntityWithoutSubclasses(entity.getPompe()));
        dto.setCiterne(CiterneMapper.fromEntityWithoutSubclasses(entity.getCiterne()));
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        return dto;
    }

    public static CiternePompe toEntity(CiternePompeDto dto){
        if (null == dto){
            return null;
        }
        CiternePompe entity = new CiternePompe();
        entity.setIdCiternePompe(dto.getIdCiternePompe());
        entity.setPompe(PompeMapper.toEntityWithoutSubclasses(dto.getPompe()));
        entity.setCiterne(CiterneMapper.toEntityWithoutSubclasses(dto.getCiterne()));
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());
        return entity;
    }


    public static CiternePompeDto fromEntityWithoutSubclasses(CiternePompe entity){
        if (null == entity){
            return null;
        }
        CiternePompeDto dto = new CiternePompeDto();
        dto.setIdCiternePompe(entity.getIdCiternePompe());
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        return dto;
    }

    public static CiternePompeDto fromEntityWithoutPompe(CiternePompe entity){
        if (null == entity){
            return null;
        }
        CiternePompeDto dto = new CiternePompeDto();
        dto.setIdCiternePompe(entity.getIdCiternePompe());
        dto.setCiterne(CiterneMapper.fromEntityWithoutSubclasses(entity.getCiterne()));
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        return dto;
    }

    public static CiternePompeDto fromEntityWithoutCiterne(CiternePompe entity){
        if (null == entity){
            return null;
        }
        CiternePompeDto dto = new CiternePompeDto();
        dto.setIdCiternePompe(entity.getIdCiternePompe());
        dto.setPompe(PompeMapper.fromEntityWithoutSubclasses(entity.getPompe()));
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        return dto;
    }


    public static List<CiternePompeDto> fromEntityListWithoutPompe(List<CiternePompe> entities){
        if (null == entities){
            return null;
        }
        return entities.stream().map(CiternePompeMapper::fromEntityWithoutPompe).collect(java.util.stream.Collectors.toList());
    }

    public static List<CiternePompeDto> fromEntityListWithoutCiterne(List<CiternePompe> entities){
        if (null == entities){
            return null;
        }
        return entities.stream().map(CiternePompeMapper::fromEntityWithoutCiterne).collect(java.util.stream.Collectors.toList());
    }

    public static List<CiternePompeDto> fromEntityWithoutSubclasses(List<CiternePompe> entities){
        if (null == entities){
            return null;
        }
        return entities.stream().map(CiternePompeMapper::fromEntityWithoutSubclasses).collect(java.util.stream.Collectors.toList());
    }

    public static List<CiternePompe> toEntityList(List<CiternePompeDto> dtos){
        if (null == dtos){
            return null;
        }
        return dtos.stream().map(CiternePompeMapper::toEntity).collect(java.util.stream.Collectors.toList());
    }

    public static List<CiternePompeDto> fromEntityList(List<CiternePompe> entities){
        if (null == entities){
            return null;
        }
        return entities.stream().map(CiternePompeMapper::fromEntity).collect(java.util.stream.Collectors.toList());
    }



}

