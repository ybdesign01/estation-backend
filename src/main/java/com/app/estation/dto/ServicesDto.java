package com.app.estation.dto;

import com.app.estation.entity.Services;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor(staticName = "builder")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ServicesDto {
    private Long id;
    @NotBlank(message = "Nom obligatoire!")
    private String nom_service;
    @NotBlank(message = "Description obligatoire!")
    private String description;
    private Set<StationDto> stations;
}
