package com.app.estation.dto;

import com.app.estation.entity.Services;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;


import java.util.Set;
import java.util.stream.Collectors;

public class ServicesDto {
    private Long id;
    @NotBlank(message = "Nom obligatoire!")
    private String nom_service;
    @NotBlank(message = "Description obligatoire!")
    private String description;
    private Set<StationDto> stations;

     public ServicesDto() {
    }

    public ServicesDto(Long id, String nom_service, String description, Set<StationDto> stations) {
        this.id = id;
        this.nom_service = nom_service;
        this.description = description;
        this.stations = stations;
    }

    public static ServicesDto fromEntity(Services services){
         ServicesDto servicesDto = new ServicesDto();
            servicesDto.setId(services.getId());
            servicesDto.setNom_service(services.getNom_service());
            servicesDto.setDescription(services.getDescription());
            Set<StationDto> stationDtos = services.getStations().stream().map(StationDto::fromEntityWithoutServices).collect(Collectors.toSet());
             System.out.println(stationDtos);
            servicesDto.setStations(stationDtos);
            return servicesDto;
    }

    public static Services toEntity(ServicesDto service) {
        Services services = new Services();
        services.setId(service.getId());
        services.setNom_service(service.getNom_service());
        services.setDescription(service.getDescription());
        return services;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom_service() {
        return nom_service;
    }

    public void setNom_service(String nom_service) {
        this.nom_service = nom_service;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<StationDto> getStations() {
        return stations;
    }

    public void setStations(Set<StationDto> stations) {
        this.stations = stations;
    }

    @Override
    public String toString() {
        return "ServicesDto{" +
                "id=" + id +
                ", nom_service='" + nom_service + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
