package com.app.estation.dto;


import com.app.estation.entity.Services;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor(staticName = "builder")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class StationDto {

    private Long id;
    @NotBlank(message = "Nom obligatoires!")
    private String nom_station;
    @NotBlank(message = "Adresse obligatoires!")
    private String adresse;
    @NotEmpty(message = "Services obligatoires!")
    private Set<ServicesDto> services;
/*
    private Set<Long> servicesId;
*/



}
