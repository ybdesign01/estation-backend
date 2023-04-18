package com.app.estation.controller;

import com.app.estation.dto.StationDto;
import com.app.estation.service.implementation.StationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/station")
public class StationController {

    @Autowired
    StationServiceImpl stationService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok().body(stationService.findAll());
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addStation(@Validated @RequestBody StationDto stationDto){
        if (stationService.addStation(stationDto)){
            return ResponseEntity.status(201).body(Map.of("msg","station_added"));
        }else{
            return ResponseEntity.badRequest().body(Map.of("msg","station_not_added"));
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateStation(@Validated @RequestBody StationDto stationDto, @PathVariable Long id){
        if (stationService.updateStation(stationDto, id)){
            return ResponseEntity.ok().body(Map.of("msg","station_updated"));
        }else{
                return ResponseEntity.badRequest().body(Map.of("msg","station_not_updated"));
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> deleteStation(@PathVariable Long id){
        if (stationService.deleteStation(id)){
            return ResponseEntity.ok().body(Map.of("msg","station_deleted"));
        }else{
            return ResponseEntity.badRequest().body(Map.of("msg","station_not_deleted"));
        }
    }


}
