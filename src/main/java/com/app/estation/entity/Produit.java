package com.app.estation.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_produit;

    private String nom_produit;
    private String prix_achat;
    private String prix_vente;

    @ManyToOne
    private Service id_service;

    @ManyToOne
    private TypeProduit type;






}
