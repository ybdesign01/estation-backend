package com.app.estation.entity;


import jakarta.persistence.*;


@Entity

public class TypeProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_type;

    private String nom_type;

    private String unite;

    public TypeProduit(Long id_type, String nom_type, String unite) {
        this.id_type = id_type;
        this.nom_type = nom_type;
        this.unite = unite;
    }

    public TypeProduit() {
    }

    public Long getId_type() {
        return id_type;
    }

    public void setId_type(Long id_type) {
        this.id_type = id_type;
    }

    public String getNom_type() {
        return nom_type;
    }

    public void setNom_type(String nom_type) {
        this.nom_type = nom_type;
    }

    public String getUnite() {
        return this.unite;
    }

    public void setUnite(final String unite) {
        this.unite = unite;
    }
}
