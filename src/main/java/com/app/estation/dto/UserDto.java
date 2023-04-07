package com.app.estation.dto;

import com.app.estation.advice.validation.InsertValidation;
import com.app.estation.advice.validation.UpdateValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;


@Getter
@Setter
@AllArgsConstructor(staticName = "builder")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserDto {

    private Long id_user;
    @NotBlank(message = "nom obligatoire!", groups = {InsertValidation.class})
    private String nom;
    @NotBlank(message = "prenom obligatore!", groups = {InsertValidation.class})
    private String prenom;
    @NotBlank(message = "email obligatore!", groups = {InsertValidation.class})
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "l'email invalide!", groups = {InsertValidation.class, UpdateValidation.class})
    private String email;
    @NotBlank(message = "mot de passe obligatore!", groups = {InsertValidation.class})
    @Length(min = 8, max = 20, message = "le mot de passe doit contenir entre 8 et 20 caracteres!", groups = {InsertValidation.class, UpdateValidation.class})
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Le mot de passe doit contenir une majuscule une miniscule et un caractere special!", groups = {InsertValidation.class, UpdateValidation.class})
    private String password;
    @NotBlank(message = "matricule obligatoire!", groups = {InsertValidation.class})
    private String matricule;
    private ProfileDto profile;
}
