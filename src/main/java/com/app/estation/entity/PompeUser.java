package com.app.estation.entity;

import com.app.estation.entity.keys.PompeUserKey;
import jakarta.persistence.*;


@Entity
public class PompeUser {

    @ManyToOne
    @MapsId("id_pompe")
    @JoinColumn(name = "id_pompe")
    Pompe pompe;
    @ManyToOne
    @MapsId("id_user")
    @JoinColumn(name = "id_user")
    User user;
    @EmbeddedId
    private PompeUserKey pompeUserKey;
    private String date_debut;
    private String date_fin;

    public PompeUser() {
    }

    public PompeUser(Pompe pompe, User user, String date_debut, String date_fin) {
        this.pompe = pompe;
        this.user = user;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public PompeUserKey getPompeUserKey() {
        return pompeUserKey;
    }

    public void setPompeUserKey(PompeUserKey pompeUserKey) {
        this.pompeUserKey = pompeUserKey;
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

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "PompeUser{" +
                ", pompeUserKey=" + pompeUserKey +
                ", date_debut='" + date_debut + '\'' +
                ", date_fin='" + date_fin + '\'' +
                '}';
    }
}
