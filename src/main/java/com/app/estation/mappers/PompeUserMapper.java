package com.app.estation.mappers;

import com.app.estation.dto.User.PompeUserDto;
import com.app.estation.entity.PompeUser;

import java.util.List;
import java.util.stream.Collectors;

public class PompeUserMapper {

    public static PompeUser toEntity(PompeUserDto dto) {
        if (dto == null) {
            return null;
        }
        PompeUser entity = new PompeUser();
        entity.setIdPompeUser(dto.getIdPompeUser());
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());
        entity.setUser(UserMapper.toEntity(dto.getUser()));
        entity.setPompe(PompeMapper.toEntity(dto.getPompe()));
        return entity;
    }

     public static PompeUserDto fromEntity(PompeUser entity) {
        if (entity == null) {
            return null;
        }
        PompeUserDto dto = new PompeUserDto();
        dto.setIdPompeUser(entity.getIdPompeUser());
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        dto.setUser(UserMapper.fromEntityWithoutSubclasses(entity.getUser()));
        dto.setPompe(PompeMapper.fromEntityWithoutSubclasses(entity.getPompe()));
        return dto;
    }

    public static PompeUserDto fromEntityWithPompe(PompeUser entity){
        if (entity == null){
            return null;
        }
        PompeUserDto dto = new PompeUserDto();
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        dto.setPompe(PompeMapper.fromEntityWithoutSubclasses(entity.getPompe()));
        return dto;
    }

    public static PompeUserDto fromEntityWithUser(PompeUser entity){
        if (entity == null){
            return null;
        }
        PompeUserDto dto = new PompeUserDto();
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        dto.setUser(UserMapper.fromEntityWithoutSubclasses(entity.getUser()));
        return dto;
    }

    public static List<PompeUser> toEntityList(List<PompeUserDto> dtoList) {
        if (dtoList == null) {
            return null;
        }
        return dtoList.stream().map(PompeUserMapper::toEntity).collect(Collectors.toList());
    }

    public static List<PompeUserDto> fromEntityList(List<PompeUser> entityList) {
        if (entityList == null) {
            return null;
        }
        return entityList.stream().map(PompeUserMapper::fromEntity).collect(Collectors.toList());
    }

    public static List<PompeUserDto> fromEntityListWithUser(List<PompeUser> entityList) {
        if (entityList == null) {
            return null;
        }
        return entityList.stream().map(PompeUserMapper::fromEntityWithUser).collect(Collectors.toList());
    }





}
