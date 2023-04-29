package com.app.estation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;


import java.util.Set;

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

    public Set<StationWithoutServices> getStations() {
        return stations;
    }

    public void setStations(Set<StationWithoutServices> stations) {
        this.stations = stations;
    }
}
