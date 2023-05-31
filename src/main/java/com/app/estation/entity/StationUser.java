package com.app.estation.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class StationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStationUser;

    @ManyToOne
    private Station station;

    @ManyToOne
    private User user;

    private LocalDateTime date_debut;
    private LocalDateTime date_fin;


    public StationUser() {
    }


    public StationUser(final Long idStationUser, final Station station, final User user, final LocalDateTime date_debut, final LocalDateTime date_fin) {
        this.idStationUser = idStationUser;
        this.station = station;
        this.user = user;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Long getIdStationUser() {
        return this.idStationUser;
    }

    public void setIdStationUser(final Long idStationUser) {
        this.idStationUser = idStationUser;
    }

    public Station getStation() {
        return this.station;
    }

    public void setStation(final Station station) {
        this.station = station;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public LocalDateTime getDate_debut() {
        return this.date_debut;
    }

    public void setDate_debut(final LocalDateTime date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDateTime getDate_fin() {
        return this.date_fin;
    }

    public void setDate_fin(final LocalDateTime date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "StationUser{" +
                "id=" + idStationUser +
                ", station=" + (station == null ? null : station.getId()) +
                ", user=" + (user==null ? null : user.getId_user()) +
                ", date_debut='" + date_debut + '\'' +
                ", date_fin='" + date_fin + '\'' +
                '}';
    }
}
