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
        return ResponseEntity.ok().body(produitService.getAllProduits());
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addProduit(@Validated @RequestBody ProduitDto produitDto){
        ProduitDto produitDto1 = produitService.addProduit(produitDto);
        if(produitDto1 == null){
            return ResponseEntity.badRequest().body("produit_not_added");
        }else{
            return ResponseEntity.ok().body(Map.of("produit",produitDto1, "msg", "produit_added"));
        }
    }

    @PostMapping(value = "/addType", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addType(@Validated @RequestBody final TypeProduitDto typeDto) {
        TypeProduitDto typeProduitDto = typeProduitService.addTypeProduit(typeDto);
        if (typeProduitDto == null){
            return ResponseEntity.badRequest().body("type_not_added");
        }else{
            return ResponseEntity.ok().body(Map.of("type_produit",typeProduitDto, "msg", "type_added"));
        }
    }

    @GetMapping(value = "/getTypes", produces = "application/json")
    public ResponseEntity<?> getTypes(){
        return ResponseEntity.ok().body(typeProduitService.getAllTypeProduits());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getProduit(@PathVariable Long id){
        ProduitDto produitDto = produitService.getProduit(id);
        if(produitDto == null) {
            return ResponseEntity.badRequest().body("produit_not_found");
        }else{
            return ResponseEntity.ok().body(produitDto);
        }
    }


}
