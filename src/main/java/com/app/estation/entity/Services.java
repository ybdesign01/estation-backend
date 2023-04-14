package com.app.estation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;


@Entity
@AllArgsConstructor(staticName = "builder")
@Data
@NoArgsConstructor
@Table(name = "services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_service;
    private String nom_service;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "station_service",
            joinColumns = @JoinColumn(name = "id_service"),
            inverseJoinColumns = @JoinColumn(name = "id_station"))
    private Set<Station> stations;

}
