package com.app.estation.entity.keys;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PompeUserKey implements Serializable {

    @Column
    Long id_pompe;

    @Column
    Long id_user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PompeUserKey that)) return false;
        return Objects.equals(this.id_pompe, that.id_pompe) && Objects.equals(this.id_user, that.id_user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id_pompe, this.id_user);
    }

    public Long getId_pompe() {
        return id_pompe;
    }

    public void setId_pompe(Long id_pompe) {
        this.id_pompe = id_pompe;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }
}
