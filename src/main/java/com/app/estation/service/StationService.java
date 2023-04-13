package com.app.estation.service;
import com.app.estation.dto.StationDto;
import com.app.estation.entity.Station;

import java.util.List;

public interface StationService {
    List<Station> findAll();
    Station getStation(Long id);
    Boolean addStation(StationDto station);
    Boolean updateStation(StationDto station, Long id);
    Boolean deleteStation(Long id);


}
