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
    public List<Services> getServices() {
        List<Services> s = serviceRepository.findAll();
        if (s.isEmpty()) {
            return null;
        }else{
            return s;
        }
    }

    public boolean addService(ServicesDto service) {
        Services s = ServicesMapper.INSTANCE.servicesDtoToServices(service);
        try {
            serviceRepository.save(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateService(ServicesDto service, Long id) {
        if (serviceRepository.findById(id).isEmpty()) {
            return false;
        }
        Services s = ServicesMapper.INSTANCE.servicesDtoToServices(service);
        try {
            serviceRepository.save(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteService(Long id) {
        if (serviceRepository.findById(id).isPresent()) {
            serviceRepository.deleteById(id);
            return true;
        }else{
            return false;
        }

    }

    public List<Services> findServicesByStationId(Long stationId){
        return serviceRepository.findServicesByStationsId(stationId);
    }

    public Services getService(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

}
