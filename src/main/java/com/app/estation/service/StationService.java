package com.app.estation.service;
import com.app.estation.dto.StationDto;
import com.app.estation.entity.Station;

import java.util.List;

public interface StationService {
    List<StationDto> findAll();
    StationDto getStation(Long id);
    StationDto addStation(StationDto station);
    StationDto updateStation(StationDto station, Long id);
    StationDto deleteStation(Long id);
    List<StationDto> findStationByServicesId(Long id);


}
