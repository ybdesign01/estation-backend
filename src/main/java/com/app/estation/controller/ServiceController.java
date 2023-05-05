package com.app.estation.controller;
import com.app.estation.dto.ServicesDto;
import com.app.estation.entity.Services;
import com.app.estation.mappers.ServicesMapper;
import com.app.estation.service.implementation.ServicesImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        List<ServicesDto> services = servicesService.getServices();
        if (services == null)
            return ResponseEntity.badRequest().body(Map.of("msg","no_services_found"));
        else {
            try {
                String json = objectMapper.writeValueAsString(services);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return ResponseEntity.ok().body(services);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getService(@PathVariable Long id){
        ServicesDto service = servicesService.getService(id);
        if (null == service){
            return ResponseEntity.badRequest().body(Map.of("msg","no_service_found"));
        }
        else{
            return ResponseEntity.ok().body(service);
        }
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addService(@Validated @RequestBody final ServicesDto service){
        ServicesDto s = servicesService.addService(service);
        if (s != null){
            return ResponseEntity.ok().body(Map.of("msg","service_added", "service", s));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg","service_not_added"));
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> updateService(@Validated @RequestBody ServicesDto service, @PathVariable Long id){
        service.setId(id);
        ServicesDto s = servicesService.updateService(service, id);
        if (s != null){
            return ResponseEntity.ok().body(Map.of("msg","service_updated", "service", s));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg","service_not_updated"));
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteService(@PathVariable Long id){
        ServicesDto s = servicesService.deleteService(id);
        if (s != null){
            return ResponseEntity.ok().body(Map.of("msg","service_deleted", "service", s));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg","service_not_deleted"));
        }
    }





}
