package com.app.estation.dto.User;

import jakarta.persistence.Column;

public class PompeUserKeyDto {

    Long id_pompe;
    Long id_user;

    public PompeUserKeyDto(Long idUser, Long id) {
        this.id_pompe = id;
        this.id_user = idUser;
    }

    public PompeUserKeyDto() {
    }

    public Long getId_pompe() {
        return this.id_pompe;
    }

    public void setId_pompe(final Long id_pompe) {
        this.id_pompe = id_pompe;
    }

    public Long getId_user() {
        return this.id_user;
    }

    public void setId_user(final Long id_user) {
        this.id_user = id_user;
    }
}
