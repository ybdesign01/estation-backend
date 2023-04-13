package com.app.estation.service.implementation;

import com.app.estation.dto.ServicesDto;
import com.app.estation.entity.Services;
import com.app.estation.repository.ServiceRepository;
import com.app.estation.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesImpl implements ServicesService {

    @Autowired
    private ServiceRepository serviceRepository;
    public List<Services> getServices() {
        return (List<Services>) serviceRepository.findAll();
    }

    public boolean addService(ServicesDto service) {
        Services s = Services.builder(
                null,
                service.getNom_service(),
                service.getDescription(),
                null
        );
        try {
            serviceRepository.save(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateService(ServicesDto service) {
        if (serviceRepository.findById(service.getId_service()).isEmpty()) {
            return false;
        }
        Services s = Services.builder(
                service.getId_service(),
                service.getNom_service(),
                service.getDescription(),
                null
        );
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

}
