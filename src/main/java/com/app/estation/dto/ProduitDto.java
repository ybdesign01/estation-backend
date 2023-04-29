package com.app.estation.dto;

import com.app.estation.entity.ProduitAction;
import com.app.estation.entity.Services;
import com.app.estation.entity.TypeProduit;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

public class ProduitDto {

    private Long id_produit;
    private String nom_produit;
    private String prix_achat;
    private String prix_vente;
    private Services id_service;
    private TypeProduit type;
    private List<ProduitActionDto> actions;
}
