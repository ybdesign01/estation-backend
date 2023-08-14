package com.app.estation.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class PompeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPompeUser;
    @ManyToOne
    Pompe pompe;
    @ManyToOne
    User user;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    @OneToMany(mappedBy="pompeUser", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Releve> releves;




    public PompeUser() {
    }

    public PompeUser(Long id_pompe_user, Pompe pompe, User user, LocalDateTime date_debut, LocalDateTime date_fin) {
        this.idPompeUser = id_pompe_user;
        this.pompe = pompe;
        this.user = user;
        this.dateDebut = date_debut;
        this.dateFin = date_fin;
    }
    public PompeUser(Pompe pompe, User user, LocalDateTime date_debut, LocalDateTime date_fin) {
        this.pompe = pompe;
        this.user = user;
        this.dateDebut = date_debut;
        this.dateFin = date_fin;
    }


    public Pompe getPompe() {
        return pompe;
    }

    public void setPompe(Pompe pompe) {
        this.pompe = pompe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Long getIdPompeUser() {
        return this.idPompeUser;
    }

    public void setIdPompeUser(final Long idPompeUser) {
        this.idPompeUser = idPompeUser;
    }

    @Override
    public String toString() {
        return "PompeUser{" +
                "idPompeUser=" + idPompeUser +
                ", pompe=" + pompe.getId_pompe() +
                ", user=" + user.getId_user() +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                '}';
    }
}
