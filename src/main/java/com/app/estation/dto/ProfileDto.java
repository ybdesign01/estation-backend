package com.app.estation.dto;

import com.app.estation.advice.validation.InsertValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


public class ProfileDto {
    private Long id_profile;
    @NotBlank(message = "nom obligatore!", groups = InsertValidation.class)
    private String nom;
    @NotBlank(message = "description obligatore!", groups = InsertValidation.class)
    private String description;

    public ProfileDto() {
    }

    public ProfileDto(Long id_profile, String nom, String description) {
        this.id_profile = id_profile;
        this.nom = nom;
        this.description = description;
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
