package com.app.estation.dto;

import com.app.estation.entity.Citerne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;

public class PompeDto {

    private Long id_pompe;
    @NotBlank(message = "nom_pompe_mandatory")
    private String nom_pompe;
    private List<Citerne> citernes;

    public PompeDto(Long id_pompe, String nom_pompe, List<Citerne> citernes) {
        this.id_pompe = id_pompe;
        this.nom_pompe = nom_pompe;
        this.citernes = citernes;
    }

    public PompeDto() {
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof PompeDto pompeDto)) return false;
        return Objects.equals(id_pompe, pompeDto.id_pompe) && Objects.equals(nom_pompe, pompeDto.nom_pompe) && Objects.equals(citernes, pompeDto.citernes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_pompe, nom_pompe, citernes);
    }

    public Long getId_pompe() {
        return this.id_pompe;
    }

    public void setId_pompe(final Long id_pompe) {
        this.id_pompe = id_pompe;
    }

    public String getNom_pompe() {
        return this.nom_pompe;
    }

    public void setNom_pompe(final String nom_pompe) {
        this.nom_pompe = nom_pompe;
    }

    public List<Citerne> getCiternes() {
        return this.citernes;
    }

    public void setCiternes(final List<Citerne> citernes) {
        this.citernes = citernes;
    }
}
