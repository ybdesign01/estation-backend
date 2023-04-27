package com.app.estation.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public class StationWithoutServices {

    private Long id;
    @NotBlank(message = "Nom obligatoires!")
    private String nom_station;
    @NotBlank(message = "Adresse obligatoires!")
    private String adresse;


    public StationWithoutServices() {
    }

    public StationWithoutServices(Long id, String nom_station, String adresse) {
        this.id = id;
        this.nom_station = nom_station;
        this.adresse = adresse;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom_station() {
        return nom_station;
    }

    public void setNom_station(String nom_station) {
        this.nom_station = nom_station;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

}
