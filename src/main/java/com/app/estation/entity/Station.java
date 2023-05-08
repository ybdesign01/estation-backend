package com.app.estation.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom_station;
    private String adresse;
    @ManyToMany(targetEntity = Services.class,  cascade =
            CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "station_service",
            joinColumns = @JoinColumn(name = "id_station"),
            inverseJoinColumns = @JoinColumn(name = "id_service"))
    private Set<Services> services;

    @OneToMany(mappedBy = "station")
    private List<StationUser> users;


    public Station(Long id, String nom_station, String adresse, Set<Services> services, List<StationUser> users) {
        this.id = id;
        this.nom_station = nom_station;
        this.users = users;
        this.adresse = adresse;
        this.services = services;
    }



    public Station() {
    }

    public List<StationUser> getUsers() {
        return this.users;
    }

    public void setUsers(final List<StationUser> users) {
        this.users = users;
    }

    public Set<Services> getServices() {
        return services;
    }

    public void setServices(Set<Services> services) {
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

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", nom_station='" + nom_station + '\'' +
                ", adresse='" + adresse + '\'' +
                ", services=" + services +
                ", users=" + users +
                '}';
    }
}
