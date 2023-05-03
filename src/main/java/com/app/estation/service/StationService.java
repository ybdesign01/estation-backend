package com.app.estation.service;
import com.app.estation.dto.StationDto;
import com.app.estation.entity.Station;

import java.util.List;

public interface StationService {
    List<StationDto> findAll();
    StationDto getStation(Long id);
    Boolean addStation(StationDto station);
    Boolean updateStation(StationDto station, Long id);
    Boolean deleteStation(Long id);
    List<Station> findStationByServicesId(Long id);


}
