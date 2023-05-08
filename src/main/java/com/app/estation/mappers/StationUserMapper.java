package com.app.estation.mappers;

import com.app.estation.dto.StationUserDto;
import com.app.estation.dto.StationUserKeyDto;
import com.app.estation.entity.Station;
import com.app.estation.entity.StationUser;
import com.app.estation.entity.User;
import com.app.estation.entity.keys.StationUserKey;

import java.util.List;

public class StationUserMapper {

    public static StationUserDto fromEntity(StationUser entity) {
        if (entity == null) {
            return null;
        }
        StationUserDto dto = new StationUserDto();
        dto.setStationUserKey(new StationUserKeyDto(entity.getStationUserKey().getId_station(), entity.getStationUserKey().getId_user()));
        dto.setStation(StationMapper.fromEntityWithoutServices(entity.getStation()));
        entity.getUser().setStations(null);
        dto.setUser(UserMapper.fromEntity(entity.getUser()));
        dto.setDate_debut(entity.getDate_debut());
        dto.setDate_fin(entity.getDate_fin());
        return dto;
    }

    public static StationUserKeyDto fromEntityKey(StationUserKey entity) {
        if (entity == null) {
            return null;
        }
        StationUserKeyDto dto = new StationUserKeyDto(entity.getId_station(), entity.getId_user());
        return dto;
    }

    public static StationUserKey toEntityKey(StationUserKeyDto dto) {
        if (dto == null) {
            return null;
        }
        StationUserKey entity = new StationUserKey(dto.getId_station(), dto.getId_user());
        return entity;
    }

    public static StationUser toEntity(StationUserDto dto) {
        if (dto == null) {
            return null;
        }
        StationUser entity = new StationUser();
        entity.setStationUserKey(new StationUserKey(dto.getStationUserKey().getId_user(), dto.getStationUserKey().getId_station()));
        entity.setStation(StationMapper.toEntity(dto.getStation()));
        entity.setUser(UserMapper.toEntity(dto.getUser()));
        entity.setDate_debut(dto.getDate_debut());
        entity.setDate_fin(dto.getDate_fin());
        System.out.println(entity);
        return entity;
    }

    public static StationUserDto fromEntityWithoutUser(StationUser entity) {
        if (entity == null) {
            return null;
        }
        StationUserDto stationUser = new StationUserDto();
        stationUser.setStation(StationMapper.fromEntityWithoutServices(entity.getStation()));
        stationUser.setDate_debut(entity.getDate_debut());
        stationUser.setDate_fin(entity.getDate_fin());
        return stationUser;
    }

    public static StationUserDto fromEntityWithoutStation(StationUser entity) {
        if (entity == null) {
            return null;
        }
        StationUserDto stationUser = new StationUserDto();
        stationUser.setUser(UserMapper.fromEntityWithoutStation(entity.getUser()));
        stationUser.setDate_debut(entity.getDate_debut());
        stationUser.setDate_fin(entity.getDate_fin());
        return stationUser;
    }

    public static List<StationUserDto> fromEntityList(List<StationUser> stationUsers) {
        if (stationUsers == null) {
            return null;
        }
        return stationUsers.stream().map(StationUserMapper::fromEntity).collect(java.util.stream.Collectors.toList());
    }

    public static List<StationUser> toEntityList(List<StationUserDto> stationUsers) {
        if (stationUsers == null) {
            return null;
        }
        return stationUsers.stream().map(StationUserMapper::toEntity).collect(java.util.stream.Collectors.toList());
    }
}
