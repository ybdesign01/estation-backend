package com.app.estation.controller;

import com.app.estation.dto.StationDto;
import com.app.estation.service.implementation.StationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/station")
public class StationController {

    @Autowired
    StationServiceImpl stationService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok().body(stationService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> addStation(@Validated @RequestBody StationDto stationDto){
        if (stationService.addStation(stationDto)){
            return ResponseEntity.ok().body("Station added successfully");
        }else{
            return ResponseEntity.badRequest().body("Error adding station");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStation(@Validated @RequestBody StationDto stationDto, @PathVariable Long id){
        if (stationService.updateStation(stationDto, id)){
            return ResponseEntity.ok().body("Station updated successfully");
        }else{
            return ResponseEntity.badRequest().body("Error updating station");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStation(@PathVariable Long id){
        if (stationService.deleteStation(id)){
            return ResponseEntity.ok().body("Station deleted successfully");
        }else{
            return ResponseEntity.badRequest().body("Error deleting station");
        }
    }


}
