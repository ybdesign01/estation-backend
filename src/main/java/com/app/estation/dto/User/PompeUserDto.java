package com.app.estation.dto.User;

import com.app.estation.dto.PompeDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PompeUserDto {
    @NotNull(message = "pompe_user_not_null")
    private Long idPompeUser;
    private PompeDto pompe;
    private UserDto user;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private boolean releve;



    public PompeUserDto() {
    }

    public PompeUserDto(final Long idPompeUser, final PompeDto pompe, final UserDto user, final LocalDateTime dateDebut, final LocalDateTime dateFin, final boolean releve) {
        this.idPompeUser = idPompeUser;
        this.pompe = pompe;
        this.user = user;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.releve = releve;
    }

    public boolean isReleve() {
        return this.releve;
    }

    public void setReleve(final boolean releve) {
        this.releve = releve;
    }

    public PompeDto getPompe() {
        return this.pompe;
    }

    public void setPompe(final PompeDto pompe) {
        this.pompe = pompe;
    }

    public UserDto getUser() {
        return this.user;
    }

    public void setUser(final UserDto user) {
        this.user = user;
    }

    public Long getIdPompeUser() {
        return this.idPompeUser;
    }

    public void setIdPompeUser(final Long idPompeUser) {
        this.idPompeUser = idPompeUser;
    }

    public LocalDateTime getDateDebut() {
        return this.dateDebut;
    }

    public void setDateDebut(final LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return this.dateFin;
    }

    public void setDateFin(final LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "PompeUserDto{" +
                "idPompeUser=" + idPompeUser +
                ", pompe=" + pompe +
                ", user=" + user +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                '}';
    }
}
