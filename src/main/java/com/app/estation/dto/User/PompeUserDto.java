package com.app.estation.dto.User;

import com.app.estation.dto.PompeDto;
import com.app.estation.entity.keys.PompeUserKey;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PompeUserDto {
    private PompeUserKeyDto pompeUserKey;
    private PompeDto pompe;
    private UserDto user;
    private String date_debut;
    private String date_fin;


    public PompeUserDto() {
    }

    public PompeUserDto(PompeUserKeyDto pompeUserKey, String date_debut, String date_fin, PompeDto pompe, UserDto user) {
        this.pompeUserKey = pompeUserKey;
        this.user = user;
        this.pompe = pompe;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof PompeUserDto that)) return false;
        return Objects.equals(pompeUserKey, that.pompeUserKey) && Objects.equals(this.pompe, that.pompe) && Objects.equals(this.user, that.user) && Objects.equals(date_debut, that.date_debut) && Objects.equals(date_fin, that.date_fin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pompeUserKey, this.pompe, this.user, date_debut, date_fin);
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

    public PompeUserKeyDto getPompeUserKey() {
        return this.pompeUserKey;
    }

    public void setPompeUserKey(final PompeUserKeyDto pompeUserKey) {
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

    @Override
    public String toString() {
        return "PompeUserDto{" +
                "pompeUserKey=" + pompeUserKey +
                ", pompe=" + (pompe == null ? null : pompe.getId_pompe())+
                ", user=" + (user == null ? null : user.getId_user())+
                ", date_debut='" + date_debut + '\'' +
                ", date_fin='" + date_fin + '\'' +
                '}';
    }
}
