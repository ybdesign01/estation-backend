package com.app.estation.dto;


import com.app.estation.entity.Station;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StationDto {

    private Long id;
    @NotBlank(message = "Nom obligatoires!")
    private String nom_station;
    @NotBlank(message = "Adresse obligatoires!")
    private String adresse;
    @NotEmpty(message = "Services obligatoires!")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<ServicesDto> services;

    private Set<StationUserDto> users;



    public StationDto() {
    }



    public StationDto(Long id, String nom_station, String adresse, Set<ServicesDto> services) {
        this.id = id;
        this.nom_station = nom_station;
        this.adresse = adresse;
        this.services = services;
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

    public Set<ServicesDto> getServices() {
        return services;
    }

    public void setServices(Set<ServicesDto> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "StationDto{" +
                "id=" + id +
                ", nom_station='" + nom_station + '\'' +
                ", adresse='" + adresse + '\'' +
                ", services=" + services +
                '}';
    }
}
