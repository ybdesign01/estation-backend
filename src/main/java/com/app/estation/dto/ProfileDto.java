package com.app.estation.dto;

import com.app.estation.advice.validation.InsertValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor(staticName = "builder")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ProfileDto {

    private Long id_profile;
    @NotBlank(message = "nom obligatore!", groups = {InsertValidation.class})
    private String nom;
    @NotBlank(message = "description obligatore!", groups = {InsertValidation.class})
    private String description;

}
