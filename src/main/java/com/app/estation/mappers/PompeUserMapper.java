package com.app.estation.mappers;

import com.app.estation.dto.User.PompeUserDto;
import com.app.estation.dto.User.PompeUserKeyDto;
import com.app.estation.entity.PompeUser;
import com.app.estation.entity.keys.PompeUserKey;

import java.util.List;

public class PompeUserMapper {

    public static PompeUser toEntity(PompeUserDto dto) {
        if (dto == null) {
            return null;
        }
        PompeUser entity = new PompeUser();
        entity.setDate_debut(dto.getDate_debut());
        entity.setDate_fin(dto.getDate_fin());
        entity.setPompeUserKey(toEntityKey(dto.getPompeUserKey()));
        return entity;
    }

     public static PompeUserDto fromEntity(PompeUser entity) {
        if (entity == null) {
            return null;
        }
        PompeUserDto dto = new PompeUserDto();
        dto.setDate_debut(entity.getDate_debut());
        dto.setDate_fin(entity.getDate_fin());
        dto.setPompeUserKey(fromEntityKey(entity.getPompeUserKey()));
        return dto;
    }


    public static PompeUserKeyDto fromEntityKey(PompeUserKey entity) {
        if (entity == null) {
            return null;
        }
        PompeUserKeyDto dto = new PompeUserKeyDto();
        dto.setId_pompe(entity.getId_pompe());
        dto.setId_user(entity.getId_user());
        return dto;
    }

    public static PompeUserKey toEntityKey(PompeUserKeyDto dto) {
        if (dto == null) {
            return null;
        }
        PompeUserKey entity = new PompeUserKey();
        entity.setId_pompe(dto.getId_pompe());
        entity.setId_user(dto.getId_user());
        return entity;
    }


    public static List<PompeUser> toEntityList(List<PompeUserDto> dtoList) {
        if (dtoList == null) {
            return null;
        }
        return dtoList.stream().map(PompeUserMapper::toEntity).collect(java.util.stream.Collectors.toList());
    }

    public static List<PompeUserDto> fromEntityList(List<PompeUser> entityList) {
        if (entityList == null) {
            return null;
        }
        return entityList.stream().map(PompeUserMapper::fromEntity).collect(java.util.stream.Collectors.toList());
    }




}
