package com.app.estation.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Releve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_releve;
    @Enumerated(EnumType.STRING)
    private TypeReleve type_releve;
    private Long compteur;

    private LocalDateTime date_releve;
    @ManyToOne
    private PompeUser pompeUser;

    public Releve(Long id_releve, TypeReleve type_releve, Long compteur, PompeUser pompeUser, LocalDateTime date_releve) {
        this.date_releve = date_releve;
        this.id_releve = id_releve;
        this.type_releve = type_releve;
        this.compteur = compteur;
        this.pompeUser = pompeUser;
    }

    public Releve() {
    }

    public boolean isSortie() {
        return TypeReleve.RELEVE_SORTIE == this.type_releve;
    }

    public boolean isEntree() {
        return TypeReleve.RELEVE_ENTREE == this.type_releve;
    }

    public PompeUser getPompeUser() {
        return this.pompeUser;
    }

    public void setPompeUser(final PompeUser pompeUser) {
        this.pompeUser = pompeUser;
    }

    public LocalDateTime getDate_releve() {
        return this.date_releve;
    }

    public void setDate_releve(final LocalDateTime date_releve) {
        this.date_releve = date_releve;
    }

    public Long getId_releve() {
        return id_releve;
    }

    public void setId_releve(Long id_releve) {
        this.id_releve = id_releve;
    }

    public TypeReleve getType_releve() {
        return type_releve;
    }

    public void setType_releve(TypeReleve type_releve) {
        this.type_releve = type_releve;
    }

    public Long getCompteur() {
        return compteur;
    }

    public void setCompteur(Long compteur) {
        this.compteur = compteur;
    }
}
