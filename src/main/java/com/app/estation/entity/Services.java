package com.app.estation.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nom_service")
    private String nomService;
    private String description;

    @ManyToOne
    private Station station;

    @OneToMany(mappedBy = "id_service")
    private List<Produit> produits;

    public List<Produit> getProduits() {
        return this.produits;
    }

    public void setProduits(final List<Produit> produits) {
        this.produits = produits;
    }

    public Services(Long id_service, String nom_service, String description) {
        this.id = id_service;
        this.nomService = nom_service;
        this.description = description;
    }

    public Services() {
    }

    public Services(String nom_service, String description) {
        this.nomService = nom_service;
        this.description = description;
    }

    public Station getStation() {
        return this.station;
    }

    public void setStation(final Station station) {
        this.station = station;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNomService() {
        return this.nomService;
    }

    public void setNomService(final String nomService) {
        this.nomService = nomService;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Services{" +
                "id=" + id +
                ", nom_service='" + nomService + '\'' +
                ", description='" + description + '\'' +
                ", station=" + station.getId() +
                '}';
    }
}
