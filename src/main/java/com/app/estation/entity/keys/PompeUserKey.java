package com.app.estation.entity.keys;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class PompeUserKey implements Serializable {

    @Column
    Long id_pompe;

    @Column
    Long id_user;



}
