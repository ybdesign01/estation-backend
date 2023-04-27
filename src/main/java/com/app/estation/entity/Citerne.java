package com.app.estation.entity;

import jakarta.persistence.*;


import java.util.List;

@Entity
public class Citerne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_citerne;
    private String nom_citerne;
    private String capacite;
    private Long id_produit;

    @ManyToMany
    @JoinTable(
            name = "citerne_pompe",
            joinColumns = @JoinColumn(name = "id_citerne"),
            inverseJoinColumns = @JoinColumn(name = "id_pompe"))
    private List<Pompe> pompes;


    public Citerne(Long id_citerne, String nom_citerne, String capacite, Long id_produit, List<Pompe> pompes) {
        this.id_citerne = id_citerne;
        this.nom_citerne = nom_citerne;
        this.capacite = capacite;
        this.id_produit = id_produit;
        this.pompes = pompes;
    }

    public Citerne() {
    }

    public Long getId_citerne() {
        return id_citerne;
    }

    public void setId_citerne(Long id_citerne) {
        this.id_citerne = id_citerne;
    }

    public String getNom_citerne() {
        return nom_citerne;
    }

    public void setNom_citerne(String nom_citerne) {
        this.nom_citerne = nom_citerne;
    }

    public String getCapacite() {
        return capacite;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    public Long getId_produit() {
        return id_produit;
    }

    public void setId_produit(Long id_produit) {
        this.id_produit = id_produit;
    }

    public List<Pompe> getPompes() {
        return pompes;
    }

    public void setPompes(List<Pompe> pompes) {
        this.pompes = pompes;
    }
}
