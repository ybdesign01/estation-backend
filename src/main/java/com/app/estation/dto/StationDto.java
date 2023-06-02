package com.app.estation.dto;


import com.app.estation.dto.User.StationUserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StationDto {

    private Long id;
    @NotBlank(message = "nom_station_mandatory")
    private String nom_station;
    @NotBlank(message = "adresse_manatory")
    private String adresse;

    private Set<ServicesDto> services;
    private List<StationUserDto> users;

    private List<CiterneDto> citernes;


    public StationDto() {
    }


    public StationDto(Long id, String nom_station, String adresse, Set<ServicesDto> services, List<StationUserDto> users, List<CiterneDto> citernes) {
        this.citernes = citernes;
        this.id = id;
        this.users = users;
        this.nom_station = nom_station;
        this.adresse = adresse;
        this.services = services;
    }

    public List<CiterneDto> getCiternes() {
        return this.citernes;
    }

    public void setCiternes(final List<CiterneDto> citernes) {
        this.citernes = citernes;
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
