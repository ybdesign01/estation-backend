package com.app.estation.controller;
import com.app.estation.dto.ServicesDto;
import com.app.estation.service.implementation.ServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    ServicesImpl servicesService;


    @GetMapping
    public ResponseEntity<?> getServices(){
        return ResponseEntity.ok(servicesService.getServices());
    }

    @PostMapping
    public ResponseEntity<?> addService(@Validated @RequestBody ServicesDto service){
        if (servicesService.addService(service)){
            return ResponseEntity.ok().body(Map.of("msg","Service added successfully"));
        }else {
            return ResponseEntity.status(500).body(Map.of("msg","Service not added"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateService(@Validated @RequestBody ServicesDto service, @PathVariable Long id){
        service.setId_service(id);
        if (servicesService.updateService(service)){
            return ResponseEntity.ok().body(Map.of("msg","Service updated successfully"));
        }else {
            return ResponseEntity.status(500).body(Map.of("msg","Service not updated, id provided is wrong"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteService(@PathVariable Long id){
        if (servicesService.deleteService(id)){
            return ResponseEntity.ok().body(Map.of("msg","Service deleted successfully"));
        }else {
            return ResponseEntity.status(500).body(Map.of("msg","Service not deleted, please check if the id is correct"));
        }
    }





}
