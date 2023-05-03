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
        List<Services> services = servicesService.getServices();
        if (services == null)
            return ResponseEntity.badRequest().body(Map.of("msg","no_services_found"));
        else {
                List<ServicesDto> dtos = services.stream().map(ServicesDto::fromEntity).toList();
            try {
                String json = objectMapper.writeValueAsString(dtos);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return ResponseEntity.ok().body(dtos);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getService(@PathVariable Long id){
        Services service = servicesService.getService(id);
        if (null == service){
            return ResponseEntity.badRequest().body(Map.of("msg","no_service_found"));
        }
        else{
            ServicesDto dto = ServicesDto.fromEntity(service);
            return ResponseEntity.ok().body(dto);
        }
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addService(@Validated @RequestBody final ServicesDto service){
        if (servicesService.addService(service)){
            return ResponseEntity.ok().body(Map.of("msg","service_added"));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg","service_not_added"));
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> updateService(@Validated @RequestBody ServicesDto service, @PathVariable Long id){
        service.setId(id);
        if (servicesService.updateService(service, id)){
            return ResponseEntity.ok().body(Map.of("msg","service_updated"));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg","service_not_updated"));
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteService(@PathVariable Long id){
        if (servicesService.deleteService(id)){
            return ResponseEntity.ok().body(Map.of("msg","service_deleted"));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg","service_not_deleted"));
        }
    }





}
