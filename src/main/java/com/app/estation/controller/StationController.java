package com.app.estation.controller;

import com.app.estation.dto.StationDto;
import com.app.estation.entity.Services;
import com.app.estation.entity.Station;
import com.app.estation.mappers.ServicesMapper;
import com.app.estation.mappers.StationMapper;
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




    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAll(){
            List<Station> stations = stationService.findAll();
            if (null == stations){
                return ResponseEntity.badRequest().body(Map.of("msg","no_station_found"));
            }
            List<StationDto> dtos = StationMapper.INSTANCE.stationListToStationDtos(stations);
            return ResponseEntity.ok().body(dtos);
    }

    @GetMapping(value = "/getServices/{id}", produces = "application/json")
    public ResponseEntity<?> getStationServices(@PathVariable Long id){
        List<Services> services = servicesService.findServicesByStationId(id);
        if (null == services){
            return ResponseEntity.badRequest().body(Map.of("msg","station_not_found"));
        } else if ( services.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("msg","no_services_found"));
        }
        return ResponseEntity.ok().body(services);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getStation(@PathVariable Long id){
        Station station = stationService.getStation(id);
        if (null == station){
            return ResponseEntity.badRequest().body(Map.of("msg","no_station_found"));
        }
        return ResponseEntity.ok().body(station);
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

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteStation(@PathVariable Long id){
        if (stationService.deleteStation(id)){
            return ResponseEntity.ok().body(Map.of("msg","station_deleted"));
        }else{
            return ResponseEntity.badRequest().body(Map.of("msg","station_not_deleted"));
        }
    }


}
