package com.app.estation.controller;

import com.app.estation.dto.ReleveDto;
import com.app.estation.entity.Releve;
import com.app.estation.service.implementation.ReleveServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        List<ReleveDto> releves = releveService.getAll();
        if (null == releves){
            return ResponseEntity.badRequest().body(Map.of("msg","no_releve_found"));
        }else{
            return ResponseEntity.ok().body(releves);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getReleve(@PathVariable Long id) {
        ReleveDto releve = releveService.getReleve(id);
        if (null == releve){
            return ResponseEntity.badRequest().body(Map.of("msg", "releve_not_found"));
    }else {
            return ResponseEntity.ok().body(releve);
        }

    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addReleve(@Validated @RequestBody ReleveDto releve){
        boolean added = releveService.addReleve(releve);
        if (added)
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("msg","releve_added"));
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

    @GetMapping(value = "/getByPompe/{id}", produces = "application/json")
    public ResponseEntity<?> getReleveByPompe(@PathVariable Long id){
        List<ReleveDto> releves = releveService.getReleveByPompe(id);
        if (null == releves){
            return ResponseEntity.badRequest().body(Map.of("msg","no_releve_found"));
        }else{
            return ResponseEntity.ok().body(releves);
        }
    }


}
