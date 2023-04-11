package com.app.estation.dto;


import com.app.estation.entity.Services;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor(staticName = "builder")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class StationDto {

    private Long id_station;
    @NotBlank(message = "Nom obligatoires!")
    private String nom_station;
    @NotBlank(message = "Ndresse obligatoires!")
    private String adresse;
    @NotBlank(message = "Services obligatoires!")
    private Set<Services> services;


}
