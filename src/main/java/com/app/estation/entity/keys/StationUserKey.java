package com.app.estation.entity.keys;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
public class StationUserKey implements Serializable {

    @Column
    Long id_station;

    @Column
    Long id_user;

}
