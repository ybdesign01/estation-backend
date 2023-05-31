package com.app.estation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
@JsonInclude(JsonInclude.Include.NON_NULL)

public class TypeProduitDto {


    private Long id_type;
    @NotEmpty(message = "nom_type_mandatory")
    private String nom_type;

    @NotEmpty(message = "unite_mandatory")
    private String unite;

    public TypeProduitDto(Long id_type, String nom_type, String unite) {
        this.unite = unite;
        this.id_type = id_type;
        this.nom_type = nom_type;
    }

    public TypeProduitDto() {
    }

    public String getUnite() {
        return this.unite;
    }

    public void setUnite(final String unite) {
        this.unite = unite;
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
