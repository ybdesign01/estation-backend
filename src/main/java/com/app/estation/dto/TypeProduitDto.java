package com.app.estation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;
@JsonInclude(JsonInclude.Include.NON_NULL)

public class TypeProduitDto {


    private Long id_type;
    @NotEmpty(message = "nom_type_mandatory")
    private String nom_type;

    public TypeProduitDto(Long id_type, String nom_type) {
        this.id_type = id_type;
        this.nom_type = nom_type;
    }

    public TypeProduitDto() {
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeProduitDto that)) return false;
        return Objects.equals(id_type, that.id_type) && Objects.equals(nom_type, that.nom_type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_type, nom_type);
    }

    public Long getId_type() {
        return this.id_type;
    }

    public void setId_type(final Long id_type) {
        this.id_type = id_type;
    }

    public String getNom_type() {
        return this.nom_type;
    }

    public void setNom_type(final String nom_type) {
        this.nom_type = nom_type;
    }
}
