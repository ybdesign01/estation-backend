package com.app.estation.entity;

import com.app.estation.entity.keys.PompeUserKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class PompeUser {

    @EmbeddedId
    private PompeUserKey pompeUserKey;

    @ManyToOne
    @MapsId("id_pompe")
    @JoinColumn(name = "id_pompe")
    Pompe pompe;

    @ManyToOne
    @MapsId("id_user")
    @JoinColumn(name = "id_user")
    User user;

    private String date_debut;
    private String date_fin;

}
