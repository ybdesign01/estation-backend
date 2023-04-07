package com.app.estation.entity.keys;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class PompeUserKey implements Serializable {

    @Column
    Long id_pompe;

    @Column
    Long id_user;

    @Override
    public int hashCode() {
        return Objects.hash(getId_pompe(), getId_user());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PompeUserKey other = (PompeUserKey) obj;
        return Objects.equals(getId_pompe(), other.getId_user())
                && Objects.equals(getId_user(), other.getId_pompe());
    }


}
