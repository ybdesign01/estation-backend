package com.app.estation.service;

import java.util.List;

import com.app.estation.dto.ServicesDto;
import com.app.estation.entity.Services;

public interface ServicesService {

    List<Services> getServices();

    Services getService(Long id);

    boolean addService(ServicesDto service);

    boolean updateService(ServicesDto service, Long id);

    boolean deleteService(Long id);

    List<Services> findServicesByStationId(Long id);







}
