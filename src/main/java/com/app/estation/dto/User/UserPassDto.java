package com.app.estation.dto.User;

import com.app.estation.advice.validation.InsertValidation;
import com.app.estation.advice.validation.UpdateValidation;
import com.app.estation.dto.ProfileDto;
import com.app.estation.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;



public class UserPassDto {

    private Long id_user;
    @NotBlank(message = "nom obligatoire!", groups = InsertValidation.class)
    private String nom;
    @NotBlank(message = "prenom obligatore!", groups = InsertValidation.class)
    private String prenom;
    @NotBlank(message = "email obligatore!", groups = InsertValidation.class)
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "l'email invalide!", groups = {InsertValidation.class, UpdateValidation.class})
    private String email;
    @NotBlank(message = "mot de passe obligatore!", groups = InsertValidation.class)
    @Length(min = 8, max = 20, message = "le mot de passe doit contenir entre 8 et 20 caracteres!", groups = {InsertValidation.class, UpdateValidation.class})
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Le mot de passe doit contenir une majuscule une miniscule et un caractere special!", groups = {InsertValidation.class, UpdateValidation.class})
    private String password;
    @NotBlank(message = "matricule obligatoire!", groups = InsertValidation.class)
    private String matricule;
    private ProfileDto profile;



    public UserPassDto() {
    }

    public UserPassDto(Long id_user, String nom, String prenom, String email, String password, String matricule, ProfileDto profile) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.matricule = matricule;
        this.profile = profile;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public ProfileDto getProfile() {
        return profile;
    }

    public void setProfile(ProfileDto profile) {
        this.profile = profile;
    }
}