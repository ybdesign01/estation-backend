package com.app.estation.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.Set;


@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom_service;
    private String description;

    @ManyToMany(mappedBy = "services", fetch = FetchType.LAZY,  cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })

    private Set<Station> stations;

    public Services(Long id_service, String nom_service, String description) {
        this.nom_service = nom_service;
        this.description = description;
    }

    public Services() {
    }

    public Services(String nom_service, String description) {
        this.nom_service = nom_service;
        this.description = description;
    }

    public Set<Station> getStations() {
        return stations;
    }
    public void setStations(Set<Station> stations) {
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

    @Override
    public String toString() {
        return "Services{" +
                "id=" + id +
                ", nom_service='" + nom_service + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
