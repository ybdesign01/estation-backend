package com.app.estation.dto;


import com.app.estation.dto.User.StationUserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StationDto {

    private Long id;
    @NotBlank(message = "nom_station_mandatory")
    private String nom_station;
    @NotBlank(message = "adresse_manatory")
    private String adresse;
    @NotEmpty(message = "services_mandatory")
    private Set<ServicesDto> services;

    private List<StationUserDto> users;



    public StationDto() {
    }



    public StationDto(Long id, String nom_station, String adresse, Set<ServicesDto> services, List<StationUserDto> users) {
        this.id = id;
        this.users = users;
        this.nom_station = nom_station;
        this.adresse = adresse;
        this.services = services;
    }

    public List<StationUserDto> getUsers() {
        return this.users;
    }

    public void setUsers(final List<StationUserDto> users) {
        this.users = users;
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
