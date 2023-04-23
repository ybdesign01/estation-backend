package com.app.estation.controller;

import com.app.estation.advice.StationSerializer;
import com.app.estation.dto.StationDto;
import com.app.estation.entity.Station;
import com.app.estation.service.implementation.StationServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/station")
public class StationController {

    @Autowired
    StationServiceImpl stationService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAll(){
        try {

            List<Station> stations = stationService.findAll();
            if (stations == null){
                return ResponseEntity.badRequest().body(Map.of("msg","no_station_found"));
            }
            SimpleModule module = new SimpleModule();
            module.addSerializer(Station.class, new StationSerializer());
            objectMapper.registerModule(module);
            String json = objectMapper.writeValueAsString(stations);
            return ResponseEntity.ok().body(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/getServices/{id}", produces = "application/json")
    public ResponseEntity<?> getStationServices(@PathVariable Long id){
        return ResponseEntity.ok().body(stationService.getServices(id));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getStation(@PathVariable Long id){
        return ResponseEntity.ok().body(stationService.getStation(id));
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
