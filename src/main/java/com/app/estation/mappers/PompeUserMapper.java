package com.app.estation.mappers;

import com.app.estation.dto.User.PompeUserDto;
import com.app.estation.dto.User.PompeUserKeyDto;
import com.app.estation.entity.PompeUser;
import com.app.estation.entity.keys.PompeUserKey;

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



    public static PompeUserKey toEntityKey(PompeUserKeyDto dto) {
        if (dto == null) {
            return null;
        }
        PompeUserKey entity = new PompeUserKey();
        entity.setId_pompe(dto.getId_pompe());
        entity.setId_user(dto.getId_user());
        return entity;
    }


}
