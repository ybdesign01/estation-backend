package com.app.estation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.io.Serializable;


@Entity
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_profile;
    private String nom;
    private String description;

    public Profile(Long id_profile, String nom, String description) {
        this.id_profile = id_profile;
        this.nom = nom;
        this.description = description;
    }

    public Profile() {
    }

    public Long getId_profile() {
        return id_profile;
    }

    public void setId_profile(Long id_profile) {
        this.id_profile = id_profile;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
