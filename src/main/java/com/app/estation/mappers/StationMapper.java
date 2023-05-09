package com.app.estation.mappers;

import com.app.estation.dto.ServicesDto;
import com.app.estation.dto.StationDto;
import com.app.estation.dto.StationUserDto;
import com.app.estation.entity.Station;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StationMapper {

    public static StationDto fromEntity(Station station){
        if (station == null) return null;
        final StationDto stationDto = new StationDto();
        stationDto.setId(station.getId());
        stationDto.setNom_station(station.getNom_station());
        stationDto.setAdresse(station.getAdresse());
        final Set<ServicesDto> servicesDtos = station.getServices().stream().map(ServicesMapper::fromEntityWithoutStations).collect(Collectors.toSet());
        if (station.getUsers() != null){
            stationDto.setUsers(station.getUsers().stream().map(StationUserMapper::fromEntityWithoutStation).toList());
        }
        stationDto.setServices(servicesDtos);
        return stationDto;
    }

    public static StationDto fromEntityWithoutServices(Station station){
        if (station == null) return null;
        StationDto stationDto = new StationDto();
        stationDto.setId(station.getId());
        stationDto.setNom_station(station.getNom_station());
        stationDto.setAdresse(station.getAdresse());
        return stationDto;
    }

    public static List<StationDto> fromEntityList(List<Station> stations) {
        if (stations == null || stations.isEmpty()) return null;
        return stations.stream().map(StationMapper::fromEntity).collect(Collectors.toList());
    }

    public static List<StationDto> fromEntityListWithoutServices(List<Station> stations) {
        if (stations == null || stations.isEmpty()) return null;
        return stations.stream().map(StationMapper::fromEntityWithoutServices).collect(Collectors.toList());
    }

    public static Station toEntity(StationDto station) {
        if (station == null) return null;
        Station station1 = new Station();
        station1.setId(station.getId());
        station1.setNom_station(station.getNom_station());
        station1.setAdresse(station.getAdresse());
        station1.setUsers(StationUserMapper.toEntityList(station.getUsers()));
        station1.setServices(ServicesMapper.toEntitySet(station.getServices()));
        return station1;
    }
}
