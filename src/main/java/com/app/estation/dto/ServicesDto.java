package com.app.estation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ServicesDto {
    private Long id;
    @NotBlank(message = "Nom obligatoire!")
    private String nom_service;
    @NotBlank(message = "Description obligatoire!")
    private String description;

    private StationDto station;

     public ServicesDto() {
    }

    public ServicesDto(Long id, String nom_service, String description, StationDto stations) {
        this.id = id;
        this.nom_service = nom_service;
        this.description = description;
        this.station = stations;
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

    public StationDto getStation() {
        return this.station;
    }

    public void setStation(final StationDto stations) {
        this.station = stations;
    }

    @Override
    public String toString() {
        return "ServicesDto{" +
                "id=" + id +
                ", nom_service='" + nom_service + '\'' +
                ", description='" + description + '\'' +
                ", stations=" + station.getId() +
                '}';
    }
}
