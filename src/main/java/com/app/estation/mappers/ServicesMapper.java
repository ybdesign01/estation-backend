package com.app.estation.mappers;

import com.app.estation.dto.ServicesDto;
import com.app.estation.dto.StationDto;
import com.app.estation.entity.Services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ServicesMapper {

    public static ServicesDto fromEntity(Services services){
        if (services == null) return null;
        ServicesDto servicesDto = new ServicesDto();
        servicesDto.setId(services.getId());
        servicesDto.setNom_service(services.getNom_service());
        servicesDto.setDescription(services.getDescription());
        if (services.getStations() != null){
            Set<StationDto> stationDtos = services.getStations().stream().map(StationMapper::fromEntityWithoutServices).collect(Collectors.toSet());
            servicesDto.setStations(stationDtos);
        }
        return servicesDto;
    }

    public static Services toEntity(ServicesDto service) {
        if (service == null) return null;
        Services services = new Services();
        services.setId(service.getId());
        services.setNom_service(service.getNom_service());
        services.setDescription(service.getDescription());
        return services;
    }

    public static ServicesDto fromEntityWithoutStations(Services services){
        ServicesDto servicesDto = new ServicesDto();
        servicesDto.setId(services.getId());
        servicesDto.setNom_service(services.getNom_service());
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
