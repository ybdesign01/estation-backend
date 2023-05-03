package com.app.estation.service.implementation;

import com.app.estation.dto.StationDto;
import com.app.estation.entity.Services;
import com.app.estation.entity.Station;
import com.app.estation.mappers.StationMapper;
import com.app.estation.repository.StationRepository;
import com.app.estation.service.StationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class StationServiceImpl implements StationService {

    @Autowired
    StationRepository stationRepository;

    @Autowired
    ServicesImpl servicesService;


    @Autowired
    private ObjectMapper objectMapper;


    public List<StationDto> findAll() {
        List<Station> stations = stationRepository.findAll();
        if (stations.isEmpty()) {
            return null;
        }else{
            return StationDto.fromEntityList(stations);
        }
    }



    public List<Services> getServices(Long id) {
        List<Services> s = servicesService.findServicesByStationId(id);
        if (s.isEmpty()) {
            return null;
        }else{
            return s;
        }
    }

    @Override
    public StationDto getStation(Long id) {
        Station station = stationRepository.findById(id).orElse(null);
        if (station == null) {
            return null;
        }else{
            return StationDto.fromEntity(station);
        }
    }

    @Override
    public Boolean addStation(StationDto station) {
            Station station1 = StationDto.toEntity(station);
            Set<Services> servicesSet = new HashSet<>();
            Set<Services> servs = station1.getServices();
            for (Services serv : servs) {
                Services s = servicesService.getService(serv.getId());
                if (null != s)
                servicesSet.add(s);
                else
                    return false;
            }
            station1.setServices(servicesSet);
            stationRepository.save(station1);
            return stationRepository.findById(station1.getId()).isPresent();

    }

    @Override
    public Boolean updateStation(StationDto station, Long id) {
        Station station1 = stationRepository.findById(id).orElse(null);
        Station station2 = StationMapper.INSTANCE.stationDtoToStation(station);
        if(null != station1){
            station1.setNom_station(station.getNom_station());
            station1.setAdresse(station.getAdresse());
            station1.setAdresse(station.getAdresse());
            station1.setServices(station2.getServices());
            stationRepository.save(station1);
            return stationRepository.findById(station1.getId()).isPresent();
        }
        return false;
    }

    @Override
    public Boolean deleteStation(Long id) {
        if (stationRepository.findById(id).isPresent()) {
            stationRepository.deleteById(id);
            return true;
        }
            return false;

    }

    @Override
    public List<Station> findStationByServicesId(Long id) {
        return stationRepository.findStationByServicesId(id);
    }

}
