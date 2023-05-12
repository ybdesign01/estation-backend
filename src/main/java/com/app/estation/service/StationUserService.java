package com.app.estation.service;

import com.app.estation.dto.User.StationUserDto;
import com.app.estation.dto.User.StationUserKeyDto;

import java.util.List;

public interface StationUserService {

    StationUserDto addStationUser(StationUserKeyDto stationUserDto);

    StationUserDto updateStationUser(StationUserDto stationUserDto);

    void deleteStationUser(StationUserDto stationUserDto);

    StationUserDto getStationUserById(Long id_user, Long id_station);

    List<StationUserDto> getAllStationsByUser(Long id_user);

    List<StationUserDto> getAllUsersByStation(Long id_station);



}
