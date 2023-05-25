package com.app.estation.dto;

import com.app.estation.dto.User.PompeUserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
@JsonInclude(JsonInclude.Include.NON_NULL)
@NotNull(message = "releve_not_null")
public class ReleveDto {
    private Long id_releve;
    @NotNull(message = "pompe_mandatory")
    private PompeUserDto pompeUser;
    private LocalDateTime date_releve;
    @NotNull(message = "compteur_mandatory")
    private Long compteur;

    public ReleveDto(Long id_releve, LocalDateTime date_releve,PompeUserDto pompeUser, Long compteur) {
        this.id_releve = id_releve;
        this.date_releve = date_releve;
        this.pompeUser = pompeUser;
        this.compteur = compteur;
    }

    public ReleveDto() {
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
