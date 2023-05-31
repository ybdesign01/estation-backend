package com.app.estation.controller;

import com.app.estation.dto.ReleveDto;
import com.app.estation.dto.ReleveResponse;
import com.app.estation.service.implementation.ReleveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getReleve(@PathVariable Long id) {
        return ResponseEntity.ok().body(releveService.get(id));
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addReleve(@Validated @RequestBody ReleveDto releve){
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("releve", releveService.add(releve),"msg","releve_added"));
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateReleve(@Validated @RequestBody ReleveDto releve, @PathVariable Long id){
        return ResponseEntity.ok().body(Map.of("releve",releveService.update(releve, id),"msg","releve_updated"));
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteReleve(@PathVariable Long id){
        boolean deleted = releveService.delete(id);
        if (deleted)
            return ResponseEntity.ok().body(Map.of("msg","releve_deleted"));
        else
            return ResponseEntity.badRequest().body(Map.of("msg","releve_not_deleted"));
    }

    @GetMapping(value = "/getByPompeUser/{id}", produces = "application/json")
    public ResponseEntity<?> getReleveByPompe(@PathVariable Long id){
        ReleveResponse releveResponse = releveService.getStatusByPompeUser(id);
            return ResponseEntity.ok().body(releveResponse);

    }


}
