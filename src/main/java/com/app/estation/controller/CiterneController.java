package com.app.estation.controller;

import com.app.estation.dto.CiterneDto;
import com.app.estation.service.implementation.CiterneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/citerne")
public class CiterneController {

    @Autowired
    private CiterneServiceImpl citerneService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getCiternes(){
        return ResponseEntity.ok().body(citerneService.getAllCiterne());
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addCiterne(@Validated @RequestBody CiterneDto citerneDto){
        return ResponseEntity.ok().body(citerneService.addCiterne(citerneDto));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getCiterne(@PathVariable Long id){
        return ResponseEntity.ok().body(citerneService.getCiterne(id));
    }

}
