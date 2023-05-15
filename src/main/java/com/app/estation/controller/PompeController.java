package com.app.estation.controller;

import com.app.estation.dto.PompeDto;
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

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getPompes(){
        if (pompeService.getAllPompes().isEmpty()){
            return ResponseEntity.badRequest().body(Map.of("msg","no_pompe_found"));
        }else{
            return ResponseEntity.ok().body(pompeService.getAllPompes());
        }
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addPompe(@Validated @RequestBody PompeDto pompeDto){
        PompeDto pompe = pompeService.addPompe(pompeDto);
        if (pompe == null){
            return ResponseEntity.badRequest().body(Map.of("msg","pompe_not_added"));
        }else {
            return ResponseEntity.ok().body(pompe);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getPompe(@PathVariable Long id){
        PompeDto pompe = pompeService.getPompe(id);
        if (pompe == null){
            return ResponseEntity.badRequest().body(Map.of("msg","pompe_not_found"));
        }else {
            return ResponseEntity.ok().body(pompe);
        }
    }


}
