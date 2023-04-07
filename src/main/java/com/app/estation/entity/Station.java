package com.app.estation.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "builder")
@Entity
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_station;
    private String nom_station;
    private String adresse;

    @ManyToMany
    @JoinTable(
            name = "station_service",
            joinColumns = @JoinColumn(name = "id_station"),
            inverseJoinColumns = @JoinColumn(name = "id_service"))
    private Set<Service> services;


}
