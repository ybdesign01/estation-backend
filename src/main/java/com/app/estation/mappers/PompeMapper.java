package com.app.estation.mappers;

import com.app.estation.dto.PompeDto;
import com.app.estation.entity.Pompe;

public class PompeMapper {

    public static Pompe toEntity(PompeDto dto){
        if (dto == null) {
            return null;
        }
        Pompe entity = new Pompe();
        entity.setId_pompe(dto.getId_pompe());
        entity.setNom_pompe(dto.getNom_pompe());
        entity.setUsers(UserMapper.toEntityList(dto.getUsers()));

    }

}
