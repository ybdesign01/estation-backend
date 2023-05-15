package com.app.estation.controller;

import com.app.estation.dto.ProduitDto;
import com.app.estation.service.implementation.ProduitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produit")
public class ProduitController {

    @Autowired
    private ProduitServiceImpl produitService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getProduits(){
        return ResponseEntity.ok().body(produitService.getAllProduits());
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addProduit(@Validated @RequestBody ProduitDto produitDto){
        return ResponseEntity.ok().body(produitService.addProduit(produitDto));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getProduit(@PathVariable Long id){
        return ResponseEntity.ok().body(produitService.getProduit(id));
    }


}
