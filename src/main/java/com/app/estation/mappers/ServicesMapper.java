package com.app.estation.mappers;

import com.app.estation.dto.ServicesDto;
import com.app.estation.entity.Services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ServicesMapper {

    public static ServicesDto fromEntity(Services services){
        if (services == null) return null;
        ServicesDto servicesDto = new ServicesDto();
        servicesDto.setId(services.getId());
        servicesDto.setNom_service(services.getNomService());
        servicesDto.setDescription(services.getDescription());
        servicesDto.setStation(StationMapper.fromEntityWithoutServices(services.getStation()));
        return servicesDto;
    }

    public static Services toEntity(ServicesDto service) {
        if (service == null) return null;
        Services services = new Services();
        services.setId(service.getId());
        services.setNomService((service.getNom_service() == null ? "" : service.getNom_service().toUpperCase()));
        services.setDescription(service.getDescription());
        services.setStation(StationMapper.toEntityWithoutServices(service.getStation()));
        return services;
    }

    public static ServicesDto fromEntityWithoutStations(Services services){
        ServicesDto servicesDto = new ServicesDto();
        servicesDto.setId(services.getId());
        servicesDto.setNom_service(services.getNomService());
        servicesDto.setDescription(services.getDescription());
        return servicesDto;
    }

    public static List<ServicesDto> fromEntityList(List<Services> services) {
        if (services == null || services.isEmpty()) return null;
        return services.stream().map(ServicesMapper::fromEntity).collect(Collectors.toList());
    }

    public static List<Services> toEntityList(List<ServicesDto> servicesDtos) {
        if (servicesDtos == null || servicesDtos.isEmpty()) return null;
        return servicesDtos.stream().map(ServicesMapper::toEntity).collect(Collectors.toList());
    }

    public static Set<ServicesDto> fromEntitySet(Set<Services> services) {
        if (services == null || services.isEmpty()) return null;
        return services.stream().map(ServicesMapper::fromEntity).collect(Collectors.toSet());
    }

    public static Set<Services> toEntitySet(Set<ServicesDto> servicesDtos) {
        if (servicesDtos == null || servicesDtos.isEmpty()) return null;
        return servicesDtos.stream().map(ServicesMapper::toEntity).collect(Collectors.toSet());
    }




}
