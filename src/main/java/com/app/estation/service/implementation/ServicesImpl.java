package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.ApiRequestException;
import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.ServicesDto;
import com.app.estation.entity.Services;
import com.app.estation.entity.Station;
import com.app.estation.mappers.ServicesMapper;
import com.app.estation.repository.ServiceRepository;
import com.app.estation.repository.StationRepository;
import com.app.estation.service.EServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class ServicesImpl implements EServices<ServicesDto,ServicesDto> {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private StationRepository stationRepository;

    @Override
    public List<ServicesDto> getAll() {
        List<Services> services = serviceRepository.findAll();
        if (services.isEmpty()) {
            throw new EntityNotFoundException("no_service_found");
        }else{
            return ServicesMapper.fromEntityList(services);
        }
    }
    @Override
    public ServicesDto add(ServicesDto service) {
        final Services s = ServicesMapper.toEntity(service);
        s.setId(null);
        List<Services> services = serviceRepository.findAll();
        if (service.getStation() != null){
            return addToStation(service,service.getStation().getId());
        }else{
            serviceRepository.save(s);
            return ServicesMapper.fromEntity(serviceRepository.findById(s.getId()).orElseThrow(()-> new ApiRequestException("service_not_added")));
        }
    }

    public ServicesDto addToStation(ServicesDto servicesDto, Long id_station){
        Station station = stationRepository.findById(id_station).orElseThrow(()-> new EntityNotFoundException("station_not_found"));
        boolean servicesByStation = stationRepository.existsByNomServiceAndStation(servicesDto.getNom_service().toUpperCase(),station);
        if (servicesByStation){
            throw new ApiRequestException("service_already_exists");
        }
        Services services = ServicesMapper.toEntity(servicesDto);
        services.setStation(station);
        serviceRepository.save(services);
        return ServicesMapper.fromEntity(serviceRepository.findById(services.getId()).orElseThrow(()-> new ApiRequestException("service_not_added")));
    }

    public ServicesDto update(ServicesDto service, Long id) {
        Services s =serviceRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("service_not_found"));
        Station station = stationRepository.findById(service.getStation().getId()).orElseThrow(()-> new EntityNotFoundException("station_not_found"));
        s.setId(id);
        s.setNomService(service.getNom_service());
        s.setDescription(service.getDescription());
        s.setStation(station);
        serviceRepository.save(s);
        return ServicesMapper.fromEntity(serviceRepository.findById(id).orElseThrow(()-> new ApiRequestException("service_not_updated")));
    }

    public boolean delete(Long id) {
        final Services s = serviceRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("service_not_found"));
        serviceRepository.delete(s);
        return serviceRepository.existsById(id);
    }

    public List<ServicesDto> findServicesByStationId(Long stationId){
        return ServicesMapper.fromEntityList(serviceRepository.findServicesByStationId(stationId).orElseThrow(()-> new EntityNotFoundException("no_service_found")));
    }

    public ServicesDto get(Long id) {
        return ServicesMapper.fromEntity(serviceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("service_not_found")));
    }


}
