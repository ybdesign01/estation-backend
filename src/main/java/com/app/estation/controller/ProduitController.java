package com.app.estation.controller;

import com.app.estation.dto.ProduitDto;
import com.app.estation.dto.TypeProduitDto;
import com.app.estation.service.implementation.ProduitServiceImpl;
import com.app.estation.service.implementation.TypeProduitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/produit")
public class ProduitController {

    @Autowired
    private ProduitServiceImpl produitService;

    @Autowired
    private TypeProduitServiceImpl typeProduitService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getProduits(){
        return ResponseEntity.ok().body(produitService.getAll());
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addProduit(@Validated @RequestBody ProduitDto produitDto){
        return ResponseEntity.ok().body(Map.of("produit",produitService.add(produitDto), "msg", "produit_added"));
    }

    @PostMapping(value = "/addType", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addType(@Validated @RequestBody final TypeProduitDto typeDto) {
        return ResponseEntity.ok().body(Map.of("type_produit",typeProduitService.add(typeDto), "msg", "type_added"));
    }

    @GetMapping(value = "/getTypes", produces = "application/json")
    public ResponseEntity<?> getTypes(){
        return ResponseEntity.ok().body(typeProduitService.getAll());

    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getProduit(@PathVariable final Long id){
        return ResponseEntity.ok().body(produitService.get(id));
    }


}
