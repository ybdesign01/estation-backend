package com.app.estation.dto;

import com.app.estation.entity.Pompe;
import jakarta.persistence.ManyToOne;

public class ReleveDto {
    private Long id_releve;
    private String date_releve;
    private Boolean type_releve;
    private Pompe pompe;
    private Long compteur;
}
