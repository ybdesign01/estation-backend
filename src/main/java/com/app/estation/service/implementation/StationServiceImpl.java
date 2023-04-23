package com.app.estation.service.implementation;

import com.app.estation.dto.StationDto;
import com.app.estation.entity.Services;
import com.app.estation.entity.Station;
import com.app.estation.repository.StationRepository;
import com.app.estation.service.StationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
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
    ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Station> findAll() {
        List<Station> stations = stationRepository.findAll();
        System.out.println("stations found" + stations);
        for (Station station : stations) {
            System.out.println("services of station: "+station.getId() +"are: " + station.getServices());
        }
        if (stations.isEmpty()) {
            return null;
        }else{
            return stations;
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
    public Station getStation(Long id) {
        return stationRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean addStation(StationDto station) {
        String json = null;
        try {
             json = objectMapper.writeValueAsString(station);
            System.out.println(json);
            Station station1 = objectMapper.readValue(json, Station.class);
            System.out.println("station1" + station1);
            Set<Services> servicesSet = new HashSet<>();
            Set<Services> servs = station1.getServices();
            System.out.println("services" + servs);
            for (Services serv : servs) {
                Services s = servicesService.getService(serv.getId());
                System.out.println("service found" + s);
                if (s != null)
                servicesSet.add(s);
                else
                    return false;
            }

            station1.setServices(servicesSet);
            stationRepository.save(station1);
            return stationRepository.findById(station1.getId()).isPresent();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean updateStation(StationDto station, Long id) {
        Station station1 = stationRepository.findById(id).orElse(null);
        Station station2 = modelMapper.map(station, Station.class);
        if(station1 != null){
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

}
