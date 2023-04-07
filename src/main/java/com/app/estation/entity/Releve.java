package com.app.estation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Releve {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_releve;
    private String date_releve;
    private Boolean type_releve;
    @ManyToOne
    private Pompe pompe;
    private Long compteur;


}
