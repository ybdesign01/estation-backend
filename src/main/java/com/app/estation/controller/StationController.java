package com.app.estation.controller;

import com.app.estation.dto.ServicesDto;
import com.app.estation.dto.StationDto;
import com.app.estation.entity.Services;
import com.app.estation.service.implementation.ServicesImpl;
import com.app.estation.service.implementation.StationServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    ServicesImpl servicesService;

    @Autowired
    ObjectMapper objectMapper;


    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAll(){
            List<StationDto> stations = stationService.findAll();
            if (null == stations ){
                return ResponseEntity.badRequest().body(Map.of("msg","no_station_found"));
            }
        try {
            String json = objectMapper.writeValueAsString(stations);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(stations);
    }

    @GetMapping(value = "/getServices/{id}", produces = "application/json")
    public ResponseEntity<?> getStationServices(@PathVariable Long id){
        List<ServicesDto> services = servicesService.findServicesByStationId(id);
        if (null == services){
            return ResponseEntity.badRequest().body(Map.of("msg","station_not_found"));
        } else if (services.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("msg","no_services_found"));
        }
        return ResponseEntity.ok().body(services);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getStation(@PathVariable Long id){
        StationDto station = stationService.getStation(id);
        if (null == station){
            return ResponseEntity.badRequest().body(Map.of("msg","station_not_found"));
        }
        return ResponseEntity.ok().body(station);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addStation(@Validated @RequestBody StationDto stationDto){
        System.out.println(stationDto);
        StationDto station = stationService.addStation(stationDto);
        if (station != null){
            return ResponseEntity.status(201).body(Map.of("msg","station_added", "station", station));
        }else{
            return ResponseEntity.badRequest().body(Map.of("msg","station_not_added"));
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateStation(@Validated @RequestBody StationDto stationDto, @PathVariable Long id){
        stationDto.setId(id);
        StationDto station = stationService.updateStation(stationDto, id);
        if (station != null){
            return ResponseEntity.ok().body(Map.of("msg","station_updated", "station", station));
        }else{
                return ResponseEntity.badRequest().body(Map.of("msg","station_not_updated"));
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteStation(@PathVariable Long id){
        StationDto station = stationService.deleteStation(id);
        if (null != station){
            return ResponseEntity.ok().body(Map.of("msg","station_deleted", "station", station));
        }else{
            return ResponseEntity.badRequest().body(Map.of("msg","station_not_deleted"));
        }
    }


}
