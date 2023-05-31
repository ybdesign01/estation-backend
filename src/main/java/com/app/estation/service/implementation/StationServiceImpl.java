package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.StationDto;
import com.app.estation.entity.Services;
import com.app.estation.entity.Station;
import com.app.estation.mappers.ServicesMapper;
import com.app.estation.mappers.StationMapper;
import com.app.estation.repository.StationRepository;
import com.app.estation.service.EServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class StationServiceImpl implements EServices<StationDto, StationDto> {

    @Autowired
    StationRepository stationRepository;

    @Autowired
    ServicesImpl servicesService;



    @Override
    public List<StationDto> getAll() {
        List<Station> stations = stationRepository.findAll();
        if (stations.isEmpty()) {
            throw new EntityNotFoundException("no_station_found");
        }else{
            return StationMapper.fromEntityList(stations);
        }
    }



    @Override
    public StationDto get(Long id) {
        return StationMapper.fromEntity(stationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("station_not_found")));
    }

    @Override
    public StationDto add(StationDto station) {
            Station station1 = StationMapper.toEntity(station);
            Set<Services> servicesSet = new HashSet<>();
            Set<Services> servs = station1.getServices();
            for (Services serv : servs) {
                Services s = ServicesMapper.toEntity(servicesService.get(serv.getId()));
                if (null != s)
                servicesSet.add(s);
                else
                    return null;
            }
            System.out.println(servicesSet);
            station1.setServices(servicesSet);
            stationRepository.save(station1);
            return StationMapper.fromEntity(stationRepository.findById(station1.getId()).orElseThrow(() -> new EntityNotFoundException("station_not_found")));

    }

    @Override
    public StationDto update(StationDto dto, Long id) {
        Station station = stationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("station_not_found"));
        station.setId(dto.getId());
        station.setNom_station(dto.getNom_station());
        station.setAdresse(dto.getAdresse());
        station.setAdresse(dto.getAdresse());
        station.setServices(ServicesMapper.toEntitySet(dto.getServices()));
        stationRepository.save(station);
        return StationMapper.fromEntity(stationRepository.findById(station.getId()).orElseThrow(() -> new EntityNotFoundException("station_not_updated")));
    }

    @Override
    public boolean delete(Long id) {
        StationDto station = StationMapper.fromEntity(stationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("station_not_found")));
            stationRepository.deleteById(id);
            return stationRepository.existsById(id);
    }


}
