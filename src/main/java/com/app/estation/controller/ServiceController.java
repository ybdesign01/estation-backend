package com.app.estation.controller;

import com.app.estation.dto.ServicesDto;
import com.app.estation.service.implementation.ServicesImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    ServicesImpl servicesService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getServices(){
        return ResponseEntity.ok().body(servicesService.getAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getService(@PathVariable Long id){
        return ResponseEntity.ok().body(servicesService.get(id));
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addService(@Validated @RequestBody final ServicesDto service){
        return ResponseEntity.ok().body(Map.of("msg","service_added", "service", servicesService.add(service)));
    }

    @PostMapping(value = "/addToStation/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addServiceToStation(@Validated @RequestBody final ServicesDto service, @PathVariable Long id){
        return ResponseEntity.ok().body(Map.of("msg","service_added", "service", servicesService.addToStation(service,id)));
    }

    @PutMapping(value = "/{id}" , consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateService(@Validated @RequestBody ServicesDto service, @PathVariable Long id){
        return ResponseEntity.ok().body(Map.of("msg","service_updated", "service", servicesService.update(service,id)));

    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteService(@PathVariable Long id){
        boolean s = servicesService.delete(id);
        if (!s){
            return ResponseEntity.ok().body(Map.of("msg","service_deleted", "service", s));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg","service_not_deleted"));
        }
    }

    @GetMapping(value = "/getByStation/{id}", produces = "application/json")
    public ResponseEntity<?> getServiceByStation(@PathVariable Long id){
        return ResponseEntity.ok().body(servicesService.findServicesByStationId(id));
    }





}
