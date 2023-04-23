package com.app.estation.controller;
import com.app.estation.dto.ServicesDto;
import com.app.estation.service.implementation.ServicesImpl;
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


    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getServices(){
        return ResponseEntity.ok(servicesService.getServices());
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addService(@Validated @RequestBody ServicesDto service){
        if (servicesService.addService(service)){
            return ResponseEntity.ok().body(Map.of("msg","service_added"));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg","service_not_added"));
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> updateService(@Validated @RequestBody ServicesDto service, @PathVariable Long id){
        service.setId(id);
        if (servicesService.updateService(service)){
            return ResponseEntity.ok().body(Map.of("msg","service_updated"));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg","service_not_updated"));
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> deleteService(@PathVariable Long id){
        if (servicesService.deleteService(id)){
            return ResponseEntity.ok().body(Map.of("msg","service_deleted"));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg","service_not_deleted"));
        }
    }





}
