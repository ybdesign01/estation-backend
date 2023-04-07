package com.app.estation.dto;

import com.app.estation.advice.validation.InsertValidation;
import com.app.estation.advice.validation.UpdateValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;


@Getter
@Setter
@AllArgsConstructor(staticName = "builder")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserPassDto {

    private Long id_user;
    @NotBlank(message = "nom obligatoire!", groups = {InsertValidation.class})
    private String nom;
    @NotBlank(message = "prenom obligatore!", groups = {InsertValidation.class})
    private String prenom;
    @NotBlank(message = "email obligatore!", groups = {InsertValidation.class})
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "l'email invalide!", groups = {InsertValidation.class, UpdateValidation.class})
    private String email;
    @NotBlank(message = "matricule obligatoire!", groups = {InsertValidation.class})
    private String matricule;
    private ProfileDto profile;

}
