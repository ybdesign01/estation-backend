package com.app.estation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor(staticName = "builder")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ServicesDto {
    private Long id_service;
    @NotBlank(message = "Nom obligatoire!")
    private String nom_service;
    @NotBlank(message = "Description obligatoire!")
    private String description;
}
