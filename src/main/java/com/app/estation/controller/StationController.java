package com.app.estation.controller;

import com.app.estation.dto.DashboardRequest;
import com.app.estation.dto.ServicesDto;
import com.app.estation.dto.StationDto;
import com.app.estation.service.implementation.ServicesImpl;
import com.app.estation.service.implementation.StationServiceImpl;
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
        List<StationDto> stations = stationService.getAll();
        return ResponseEntity.ok().body(stations);
    }

    @GetMapping(value = "/getServices/{id}", produces = "application/json")
    public ResponseEntity<?> getStationServices(@PathVariable Long id){
        List<ServicesDto> services = servicesService.findServicesByStationId(id);
        if (null == services){
            return ResponseEntity.badRequest().body(Map.of("msg","station_not_found"));
        }
        return ResponseEntity.ok().body(services);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getStation(@PathVariable Long id){
        return ResponseEntity.ok().body(stationService.get(id));
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addStation(@Validated @RequestBody StationDto stationDto){
        return ResponseEntity.status(201).body(Map.of("msg","station_added", "station", stationService.add(stationDto)));
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateStation(@Validated @RequestBody final StationDto stationDto, @PathVariable Long id){
        return ResponseEntity.ok().body(Map.of("msg","station_updated", "station", stationService.update(stationDto, id)));
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteStation(@PathVariable Long id){
        final boolean deleted = stationService.delete(id);
        if (!deleted){
            return ResponseEntity.ok().body(Map.of("msg","station_deleted"));
        }else{
            return ResponseEntity.badRequest().body(Map.of("msg","station_not_deleted"));
        }
    }

    @GetMapping(value = "/getInfo/{id}", produces = "application/json")
    public ResponseEntity<?> getStationInfo(@PathVariable Long id){
        return ResponseEntity.ok().body(stationService.getStationInformation(id));
    }

    @PostMapping(value = "/getDashboardInfo/{id}", produces = "application/json",consumes = "application/json")
    public ResponseEntity<?> getStationDashboardInfo(@PathVariable Long id, @RequestBody DashboardRequest request){
        return ResponseEntity.ok().body(stationService.getDashboardInfo(id, request));
    }




}
