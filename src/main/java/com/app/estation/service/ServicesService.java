package com.app.estation.service;

import java.util.List;

import com.app.estation.dto.ServicesDto;
import com.app.estation.entity.Services;

public interface ServicesService {

    List<ServicesDto> getServices();

    ServicesDto getService(Long id);

    ServicesDto addService(ServicesDto service);

    ServicesDto updateService(ServicesDto service, Long id);

    ServicesDto deleteService(Long id);

    List<ServicesDto> findServicesByStationId(Long id);







}
