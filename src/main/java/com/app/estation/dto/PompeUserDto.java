package com.app.estation.dto;

import com.app.estation.entity.keys.PompeUserKey;

import java.util.Objects;

public class PompeUserDto {
    private PompeUserKey pompeUserKey;
    private String date_debut;
    private String date_fin;

    public PompeUserDto() {
    }

    public PompeUserDto(PompeUserKey pompeUserKey, String date_debut, String date_fin) {
        this.pompeUserKey = pompeUserKey;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof PompeUserDto that)) return false;
        return Objects.equals(pompeUserKey, that.pompeUserKey) && Objects.equals(date_debut, that.date_debut) && Objects.equals(date_fin, that.date_fin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pompeUserKey, date_debut, date_fin);
    }

    public PompeUserKey getPompeUserKey() {
        return this.pompeUserKey;
    }

    public void setPompeUserKey(final PompeUserKey pompeUserKey) {
        this.pompeUserKey = pompeUserKey;
    }

    public String getDate_debut() {
        return this.date_debut;
    }

    public void setDate_debut(final String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return this.date_fin;
    }

    public void setDate_fin(final String date_fin) {
        this.date_fin = date_fin;
    }
}
