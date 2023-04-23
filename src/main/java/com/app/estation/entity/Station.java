package com.app.estation.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "builder")
@Entity
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom_station;
    private String adresse;
    @ManyToMany(targetEntity = Services.class,  cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "station_service",
            joinColumns = @JoinColumn(name = "id_station"),
            inverseJoinColumns = @JoinColumn(name = "id_service"))
    private Set<Services> services;

    public Set<Services> getServices() {
        return services;
    }

    public void setServices(Set<Services> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", nom_station='" + nom_station + '\'' +
                ", adresse='" + adresse + '\'' +
                ", services=" + services +
                '}';
    }
}
