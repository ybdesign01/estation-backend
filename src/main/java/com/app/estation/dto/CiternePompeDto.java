package com.app.estation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CiternePompeDto {

    private Long idCiternePompe;
    private PompeDto pompe;
    private CiterneDto citerne;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    public Long getIdCiternePompe() {
        return idCiternePompe;
    }

    public void setIdCiternePompe(Long idCiternePompe) {
        this.idCiternePompe = idCiternePompe;
    }

    public PompeDto getPompe() {
        return pompe;
    }

    public void setPompe(PompeDto pompe) {
        this.pompe = pompe;
    }

    public CiterneDto getCiterne() {
        return citerne;
    }

    public void setCiterne(CiterneDto citerne) {
        this.citerne = citerne;
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
        return "CiternePompeDto{" + "idCiternePompe=" + idCiternePompe + ", pompe=" + pompe + ", citerne=" + citerne + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }
}
