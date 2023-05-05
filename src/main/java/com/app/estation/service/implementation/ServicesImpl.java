package com.app.estation.service.implementation;

import com.app.estation.dto.ServicesDto;
import com.app.estation.entity.Services;
import com.app.estation.mappers.ServicesMapper;
import com.app.estation.repository.ServiceRepository;
import com.app.estation.service.ServicesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class ServicesImpl implements ServicesService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<ServicesDto> getServices() {
        List<ServicesDto> s = ServicesMapper.fromEntityList(serviceRepository.findAll());
        if (s == null) {
            return null;
        }else{
            return s;
        }
    }
    @Override
    public ServicesDto addService(ServicesDto service) {
        final Services s = ServicesMapper.toEntity(service);
        try {
            serviceRepository.save(s);
            return ServicesMapper.fromEntity(serviceRepository.findById(s.getId()).orElse(null));
        } catch (Exception e) {
            return null;
        }
    }

    public ServicesDto updateService(ServicesDto service, Long id) {
        Services s =serviceRepository.findById(id).orElse(null);
        if (s == null) {
            return null;
        }
        s.setNom_service(service.getNom_service());
        s.setDescription(service.getDescription());
        try {
            serviceRepository.save(s);
            return ServicesMapper.fromEntity(s);
        } catch (Exception e) {
            return null;
        }
    }

    public ServicesDto deleteService(Long id) {
        Services s = serviceRepository.findById(id).orElse(null);
        if (s == null) {
            return null;
        }
        try {
            serviceRepository.delete(s);
            return ServicesMapper.fromEntity(s);
        } catch (Exception e) {
            return null;
        }
    }

    public List<ServicesDto> findServicesByStationId(Long stationId){
        return ServicesMapper.fromEntityList(serviceRepository.findServicesByStationsId(stationId));
    }

    public ServicesDto getService(Long id) {
        return ServicesMapper.fromEntity(serviceRepository.findById(id).orElse(null));
    }

}
