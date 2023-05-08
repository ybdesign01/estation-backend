package com.app.estation.service.implementation;

import com.app.estation.dto.StationDto;
import com.app.estation.entity.Services;
import com.app.estation.entity.Station;
import com.app.estation.mappers.ServicesMapper;
import com.app.estation.mappers.StationMapper;
import com.app.estation.repository.StationRepository;
import com.app.estation.service.StationService;
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


    @Override
    public List<StationDto> findAll() {
        List<Station> stations = stationRepository.findAll();
        if (stations.isEmpty()) {
            return null;
        }else{
            System.out.println(stations.get(0).getUsers());
            return StationMapper.fromEntityList(stations);
        }
    }



    @Override
    public StationDto getStation(Long id) {
        Station station = stationRepository.findById(id).orElse(null);
        if (station == null) {
            return null;
        }else{
            return StationMapper.fromEntity(station);
        }
    }

    @Override
    public StationDto addStation(StationDto station) {
            Station station1 = StationMapper.toEntity(station);
            Set<Services> servicesSet = new HashSet<>();
            Set<Services> servs = station1.getServices();
            for (Services serv : servs) {
                Services s = ServicesMapper.toEntity(servicesService.getService(serv.getId()));
                if (null != s)
                servicesSet.add(s);
                else
                    return null;
            }
            System.out.println(servicesSet);
            station1.setServices(servicesSet);
            stationRepository.save(station1);
            return StationMapper.fromEntity(stationRepository.findById(station1.getId()).orElse(null));

    }

    @Override
    public StationDto updateStation(StationDto station, Long id) {
        Station station1 = stationRepository.findById(id).orElse(null);
        Station station2 = StationMapper.toEntity(station);
        if(station1 == null){
            return null;
        }
        station1.setNom_station(station.getNom_station());
            station1.setAdresse(station.getAdresse());
            station1.setAdresse(station.getAdresse());
            station1.setServices(station2.getServices());
            stationRepository.save(station1);
            return StationMapper.fromEntity(stationRepository.findById(station1.getId()).orElse(null));


    }

    @Override
    public StationDto deleteStation(Long id) {
        StationDto station = StationMapper.fromEntity(stationRepository.findById(id).orElse(null));
            if(station == null) {
                return null;
            }
            stationRepository.deleteById(id);
            return station;
    }

    @Override
    public List<StationDto> findStationByServicesId(Long id) {
        return StationMapper.fromEntityList(stationRepository.findStationByServicesId(id));
    }

}
