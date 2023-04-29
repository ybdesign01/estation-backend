package com.app.estation.dto;

import com.app.estation.entity.Produit;
import com.app.estation.entity.TypeAction;
import jakarta.persistence.*;

public class ProduitActionDto {

    private Long id_action;
    private Produit produit;
    private String date_action;
    private String quantite;
    private TypeAction action;
    private String fournisseur;
}
