package com.app.estation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;


@AllArgsConstructor(staticName = "builder")
@NoArgsConstructor
@Entity
@Data
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_profile;
    private String nom;
    private String description;

}
