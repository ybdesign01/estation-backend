package com.app.estation.controller;

import com.app.estation.dto.ReleveDto;
import com.app.estation.entity.Releve;
import com.app.estation.service.implementation.ReleveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> getReleve(Long id){
        return ResponseEntity.ok().body(releveService.getReleve(id));
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addReleve(ReleveDto releve){
        boolean added = releveService.addReleve(releve);
        if (added)
            return ResponseEntity.ok().body("releve_added");
        else
            return ResponseEntity.badRequest().body("releve_not_added");
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateReleve(ReleveDto releve, Long id){
        releve.setId_releve(id);
        boolean updated = releveService.updateReleve( id, releve);
        if (updated)
            return ResponseEntity.ok().body("releve_updated");
        else
            return ResponseEntity.badRequest().body("releve_not_updated");
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteReleve(Long id){
        boolean deleted = releveService.deleteReleve(id);
        if (deleted)
            return ResponseEntity.ok().body("releve_deleted");
        else
            return ResponseEntity.badRequest().body("releve_not_deleted");
    }

}
