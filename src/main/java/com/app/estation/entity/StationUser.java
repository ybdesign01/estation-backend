package com.app.estation.entity;

import com.app.estation.entity.keys.StationUserKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class StationUser {

    @EmbeddedId
    private StationUserKey stationUserKey;

    @ManyToOne
    @MapsId("id_station")
    @JoinColumn(name = "id_station")
    Station station;

    @ManyToOne
    @MapsId("id_user")
    @JoinColumn(name = "id_user")
    User user;

    private String date_debut;
    private String date_fin;

}
