package com.app.estation.dto;

import java.time.LocalDateTime;

public class CiternePompeRequest {

    private Long idCiternePompe;
    private Long idPompe;
    private Long idCiterne;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    public CiternePompeRequest() {
    }

    public CiternePompeRequest(final Long idCiternePompe, final Long idPompe, final Long idCiterne, final LocalDateTime dateDebut, final LocalDateTime dateFin) {
        this.idCiternePompe = idCiternePompe;
        this.idPompe = idPompe;
        this.idCiterne = idCiterne;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Long getIdCiternePompe() {
        return this.idCiternePompe;
    }

    public void setIdCiternePompe(final Long idCiternePompe) {
        this.idCiternePompe = idCiternePompe;
    }

    public Long getIdPompe() {
        return this.idPompe;
    }

    public void setIdPompe(final Long idPompe) {
        this.idPompe = idPompe;
    }

    public Long getIdCiterne() {
        return this.idCiterne;
    }

    public void setIdCiterne(final Long idCiterne) {
        this.idCiterne = idCiterne;
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
}
