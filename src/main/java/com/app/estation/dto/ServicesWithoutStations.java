package com.app.estation.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public class ServicesWithoutStations {
    private Long id;
    @NotBlank(message = "Nom obligatoire!")
    private String nom_service;
    @NotBlank(message = "Description obligatoire!")
    private String description;


     public ServicesWithoutStations() {
    }

    public ServicesWithoutStations(Long id, String nom_service, String description) {
        this.id = id;
        this.nom_service = nom_service;
        this.description = description;
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

}
