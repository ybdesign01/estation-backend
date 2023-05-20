package com.app.estation.controller;

import com.app.estation.dto.ReleveDto;
import com.app.estation.entity.Releve;
import com.app.estation.service.implementation.ReleveServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/releve")
public class ReleveController {

    @Autowired
    ReleveServiceImpl releveService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getReleves(){
        return ResponseEntity.ok().body(releveService.getAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getReleve(@PathVariable Long id){
        return ResponseEntity.ok().body(releveService.getReleve(id));
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addReleve(@Validated @RequestBody ReleveDto releve){
        boolean added = releveService.addReleve(releve);
        if (added)
            return ResponseEntity.ok().body(Map.of("msg","releve_added"));
        else
            return ResponseEntity.badRequest().body(Map.of("msg","releve_not_added"));
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateReleve(@Validated @RequestBody ReleveDto releve, @PathVariable Long id){
        releve.setId_releve(id);
        boolean updated = releveService.updateReleve( id, releve);
        if (updated)
            return ResponseEntity.ok().body(Map.of("msg","releve_updated"));
        else
            return ResponseEntity.badRequest().body(Map.of("msg","releve_not_updated"));
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteReleve(@PathVariable Long id){
        boolean deleted = releveService.deleteReleve(id);
        if (deleted)
            return ResponseEntity.ok().body(Map.of("msg","releve_deleted"));
        else
            return ResponseEntity.badRequest().body(Map.of("msg","releve_not_deleted"));
    }

}
