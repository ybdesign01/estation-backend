package com.app.estation.entity;

import jakarta.persistence.*;


import java.util.List;

@Entity
public class Citerne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_citerne;
    private String nom_citerne;
    private Double capacite;
    @ManyToOne
    private Produit id_produit;

    @OneToMany(mappedBy = "citerne")
    private List<CiternePompe> pompes;

    @ManyToOne
    private Station station;



    public Citerne(Long id_citerne, String nom_citerne, Double capacite, Produit id_produit, List<CiternePompe> pompes, Station station) {
        this.id_citerne = id_citerne;
        this.nom_citerne = nom_citerne;
        this.capacite = capacite;
        this.id_produit = id_produit;
        this.pompes = pompes;
        this.station = station;
    }

    public Citerne() {
    }

    public Station getStation() {
        return this.station;
    }

    public void setStation(final Station station) {
        this.station = station;
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

    public Double getCapacite() {
        return capacite;
    }

    public void setCapacite(Double capacite) {
        this.capacite = capacite;
    }

    public Produit getId_produit() {
        return id_produit;
    }

    public void setId_produit(Produit id_produit) {
        this.id_produit = id_produit;
    }

    public List<CiternePompe> getPompes() {
        return this.pompes;
    }

    public void setPompes(final List<CiternePompe> pompes) {
        this.pompes = pompes;
    }

    @Override
    public String toString() {
        return "Citerne{" +
                "id_citerne=" + id_citerne +
                ", nom_citerne='" + nom_citerne + '\'' +
                ", capacite='" + capacite + '\'' +
                ", id_produit=" + id_produit.getPrix_vente() +
                ", pompes=" + pompes +
                ", station=" + station.getNom_station() +
                '}';
    }
}
