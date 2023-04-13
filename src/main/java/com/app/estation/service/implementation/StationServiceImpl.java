package com.app.estation.service.implementation;

import com.app.estation.dto.StationDto;
import com.app.estation.entity.Station;
import com.app.estation.repository.StationRepository;
import com.app.estation.service.StationService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class StationServiceImpl implements StationService {

    @Autowired
    StationRepository stationRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Station> findAll() {
        return stationRepository.findAll();
    }

    @Override
    public Station getStation(Long id) {
        return stationRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean addStation(StationDto station) {
        Station station1 = modelMapper.map(station, Station.class);
        stationRepository.save(station1);
        return stationRepository.findById(station1.getId_station()).isPresent();
    }

    @Override
    public Boolean updateStation(StationDto station, Long id) {
        Station station1 = stationRepository.findById(id).orElse(null);
        if(station1 != null){
            station1.setNom_station(station.getNom_station());
            station1.setAdresse(station.getAdresse());
            station1.setAdresse(station.getAdresse());
            station1.setServices(station.getServices());
            stationRepository.save(station1);
            return stationRepository.findById(station1.getId_station()).isPresent();
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
