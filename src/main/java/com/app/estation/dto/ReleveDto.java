package com.app.estation.dto;

import com.app.estation.entity.Pompe;
import com.app.estation.entity.TypeReleve;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
@JsonInclude(JsonInclude.Include.NON_NULL)
@NotNull(message = "releve_not_null")
public class ReleveDto {
    private Long id_releve;
    @NotBlank(message = "date_releve_mandatory")
    private String date_releve;
    @NotNull(message = "type_releve_mandatory")
    private TypeReleve type_releve;
    @NotNull(message = "pompe_mandatory")
    private PompeDto pompe;
    @NotNull(message = "compteur_mandatory")
    private Long compteur;

    public ReleveDto(Long id_releve, String date_releve, TypeReleve type_releve, PompeDto pompe, Long compteur) {
        this.id_releve = id_releve;
        this.date_releve = date_releve;
        this.type_releve = type_releve;
        this.pompe = pompe;
        this.compteur = compteur;
    }

    public ReleveDto() {
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof ReleveDto releveDto)) return false;
        return Objects.equals(id_releve, releveDto.id_releve) && Objects.equals(date_releve, releveDto.date_releve) && type_releve == releveDto.type_releve && Objects.equals(pompe, releveDto.pompe) && Objects.equals(compteur, releveDto.compteur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_releve, date_releve, type_releve, pompe, compteur);
    }

    public Long getId_releve() {
        return this.id_releve;
    }

    public void setId_releve(final Long id_releve) {
        this.id_releve = id_releve;
    }

    public String getDate_releve() {
        return this.date_releve;
    }

    public void setDate_releve(final String date_releve) {
        this.date_releve = date_releve;
    }

    public TypeReleve getType_releve() {
        return this.type_releve;
    }

    public void setType_releve(final TypeReleve type_releve) {
        this.type_releve = type_releve;
    }

    public PompeDto getPompe() {
        return this.pompe;
    }

    public void setPompe(final PompeDto pompe) {
        this.pompe = pompe;
    }

    public Long getCompteur() {
        return this.compteur;
    }

    public void setCompteur(final Long compteur) {
        this.compteur = compteur;
    }
}
