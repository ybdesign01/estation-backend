package com.app.estation.dto;


import com.app.estation.entity.Station;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private Set<ServicesDto> services;


    public StationDto() {
    }

    public static StationDto fromEntity(Station station){
        StationDto stationDto = new StationDto();
        stationDto.setId(stationDto.getId());
        stationDto.setNom_station(stationDto.getNom_station());
        stationDto.setAdresse(stationDto.getAdresse());
        Set<ServicesDto> servicesDtos = station.getServices().stream().map(ServicesDto::fromEntity).collect(Collectors.toSet());
        stationDto.setServices(servicesDtos);
        return stationDto;
    }

    public static StationDto fromEntityWithoutServices(Station station){
        StationDto stationDto = new StationDto();
        stationDto.setId(station.getId());
        stationDto.setNom_station(station.getNom_station());
        stationDto.setAdresse(station.getAdresse());
        return stationDto;
    }

    public StationDto(Long id, String nom_station, String adresse, Set<ServicesDto> services) {
        this.id = id;
        this.nom_station = nom_station;
        this.adresse = adresse;
        this.services = services;
    }

    public static List<StationDto> fromEntityList(List<Station> stations) {
        return stations.stream().map(StationDto::fromEntity).collect(Collectors.toList());
    }

    public static Station toEntity(StationDto station) {
        Station station1 = new Station();
        station1.setId(station.getId());
        station1.setNom_station(station.getNom_station());
        station1.setAdresse(station.getAdresse());

        return station1;
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
