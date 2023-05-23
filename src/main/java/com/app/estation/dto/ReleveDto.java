package com.app.estation.dto;

import com.app.estation.dto.User.PompeUserDto;
import com.app.estation.entity.TypeReleve;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;
@JsonInclude(JsonInclude.Include.NON_NULL)
@NotNull(message = "releve_not_null")
public class ReleveDto {
    private Long id_releve;
    @NotNull(message = "type_releve_mandatory")
    private TypeReleve type_releve;
    @NotNull(message = "pompe_mandatory")
    private PompeUserDto pompeUser;
    private LocalDateTime date_releve;
    @NotNull(message = "compteur_mandatory")
    private Long compteur;

    public ReleveDto(Long id_releve, LocalDateTime date_releve, TypeReleve type_releve, PompeUserDto pompeUser, Long compteur) {
        this.id_releve = id_releve;
        this.date_releve = date_releve;
        this.type_releve = type_releve;
        this.pompeUser = pompeUser;
        this.compteur = compteur;
    }

    public ReleveDto() {
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof ReleveDto releveDto)) return false;
        return Objects.equals(id_releve, releveDto.id_releve) && Objects.equals(date_releve, releveDto.date_releve) && type_releve == releveDto.type_releve && Objects.equals(pompeUser, releveDto.pompeUser) && Objects.equals(compteur, releveDto.compteur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_releve, date_releve, type_releve, pompeUser, compteur);
    }

    public Long getId_releve() {
        return this.id_releve;
    }

    public void setId_releve(final Long id_releve) {
        this.id_releve = id_releve;
    }

    public LocalDateTime getDate_releve() {
        return this.date_releve;
    }

    public void setDate_releve(final LocalDateTime date_releve) {
        this.date_releve = date_releve;
    }

    public TypeReleve getType_releve() {
        return this.type_releve;
    }

    public void setType_releve(final TypeReleve type_releve) {
        this.type_releve = type_releve;
    }

    public PompeUserDto getPompeUser() {
        return this.pompeUser;
    }
    public void setPompeUser(final PompeUserDto pompeUser) {
        this.pompeUser = pompeUser;
    }

    public Long getCompteur() {
        return this.compteur;
    }

    public void setCompteur(final Long compteur) {
        this.compteur = compteur;
    }
}
