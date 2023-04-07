package com.app.estation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitAction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_action;
    @ManyToOne
    private Produit produit;
    private String date_action;
    private String quantite;
    private Boolean action;
    private String fournisseur;
}
