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

    @PostMapping("/addService")
    public ResponseEntity<?> addService(@Validated @RequestBody ServicesDto service){
        if (servicesService.addService(service)){
            return ResponseEntity.ok().body(Map.of("msg","Service added successfully"));
        }else {
            return ResponseEntity.status(500).body(Map.of("msg","Service not added"));
        }
    }



}
