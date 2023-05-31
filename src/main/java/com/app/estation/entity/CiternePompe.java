package com.app.estation.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class CiternePompe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCiternePompe;

    @ManyToOne
    private Pompe pompe;

    @ManyToOne
    private Citerne citerne;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    public CiternePompe() {
    }

    public CiternePompe(final Long idCiternePompe, final Pompe pompe, final Citerne citerne, final LocalDateTime dateDebut, final LocalDateTime dateFin) {
        this.idCiternePompe = idCiternePompe;
        this.pompe = pompe;
        this.citerne = citerne;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Long getIdCiternePompe() {
        return this.idCiternePompe;
    }

    public void setIdCiternePompe(final Long idCiternePompe) {
        this.idCiternePompe = idCiternePompe;
    }

    public Pompe getPompe() {
        return this.pompe;
    }

    public void setPompe(final Pompe pompe) {
        this.pompe = pompe;
    }

    public Citerne getCiterne() {
        return this.citerne;
    }

    public void setCiterne(final Citerne citerne) {
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
}
