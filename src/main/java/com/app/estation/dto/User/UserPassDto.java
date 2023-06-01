package com.app.estation.dto.User;

import com.app.estation.advice.validation.InsertValidation;
import com.app.estation.advice.validation.UpdateValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;



public class UserPassDto {

    private Long id_user;
    @NotBlank(message = "nom_mandatory", groups = InsertValidation.class)
    private String nom;
    @NotBlank(message = "prenom_mandatory", groups = InsertValidation.class)
    private String prenom;
    @NotBlank(message = "email_mandatory", groups = InsertValidation.class)
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "email_invalid", groups = {InsertValidation.class, UpdateValidation.class})
    private String email;
    @NotBlank(message = "password_mandatory", groups = InsertValidation.class)
    @Length(min = 8, max = 20, message = "password_number_chars", groups = {InsertValidation.class, UpdateValidation.class})
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "password_special_chars", groups = {InsertValidation.class, UpdateValidation.class})
    private String password;
    @NotBlank(message = "matricule_mandatory", groups = InsertValidation.class)
    private String matricule;

    @NotBlank(message = "profile_mandatory")
    private String profile;



    public UserPassDto() {
    }

    public UserPassDto(Long id_user, String nom, String prenom, String email, String password, String matricule, String profile) {
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
