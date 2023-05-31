package com.app.estation.controller;

import com.app.estation.dto.CiternePompeRequest;
import com.app.estation.dto.PompeDto;
import com.app.estation.service.implementation.CiternePompeServiceImpl;
import com.app.estation.service.implementation.PompeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pompe")
public class PompeController {

    @Autowired
    private PompeServiceImpl pompeService;

    @Autowired
    CiternePompeServiceImpl citernePompeService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getPompes(){
        return ResponseEntity.ok().body(pompeService.getAll());
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addPompe(@Validated @RequestBody PompeDto pompeDto){
            return ResponseEntity.ok().body(Map.of("msg","pompe_added", "pompe",pompeService.add(pompeDto)));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getPompe(@PathVariable Long id){
        return ResponseEntity.ok().body(pompeService.get(id));
    }


    @PostMapping(value = "/setCiterne", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> setCiterne(@Validated @RequestBody CiternePompeRequest request){
        return ResponseEntity.ok().body(Map.of("msg","citerne_set", "citerne_pompe",citernePompeService.setCiterne(request)));
    }

}
