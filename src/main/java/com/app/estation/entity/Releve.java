package com.app.estation.entity;

import jakarta.persistence.*;


@Entity
public class Releve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_releve;
    private String date_releve;
    @Enumerated(EnumType.STRING)
    private TypeReleve type_releve;
    @ManyToOne
    private Pompe pompe;
    private Long compteur;

    public Releve(Long id_releve, String date_releve, TypeReleve type_releve, Pompe pompe, Long compteur) {
        this.id_releve = id_releve;
        this.date_releve = date_releve;
        this.type_releve = type_releve;
        this.pompe = pompe;
        this.compteur = compteur;
    }

    public Releve() {
    }

    public Long getId_releve() {
        return id_releve;
    }

    public void setId_releve(Long id_releve) {
        this.id_releve = id_releve;
    }

    public String getDate_releve() {
        return date_releve;
    }

    public void setDate_releve(String date_releve) {
        this.date_releve = date_releve;
    }

    public TypeReleve getType_releve() {
        return type_releve;
    }

    public void setType_releve(TypeReleve type_releve) {
        this.type_releve = type_releve;
    }

    public Pompe getPompe() {
        return pompe;
    }

    public void setPompe(Pompe pompe) {
        this.pompe = pompe;
    }

    public Long getCompteur() {
        return compteur;
    }

    public void setCompteur(Long compteur) {
        this.compteur = compteur;
    }
}
