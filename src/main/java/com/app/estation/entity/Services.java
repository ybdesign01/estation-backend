package com.app.estation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.HashSet;
import java.util.Set;


@Entity

@AllArgsConstructor(staticName = "builder")
@Getter
@Setter
@NoArgsConstructor
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
    @JsonIgnore
    private Set<Station> stations;

    public Services(Long id_service, String nom_service, String description) {
        this.nom_service = nom_service;
        this.description = description;
    }

    public Set<Station> getStations() {
        return stations;
    }
    public void setStations(Set<Station> stations) {
        this.stations = stations;
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
