package com.app.estation.controller;

import com.app.estation.dto.CiterneDto;
import com.app.estation.dto.PompeDto;
import com.app.estation.service.implementation.CiterneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/citerne")
public class CiterneController {

    @Autowired
    private CiterneServiceImpl citerneService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getCiternes(){
        List<CiterneDto> citerneDtos = citerneService.getAllCiterne();
        if (citerneDtos == null) {
            return ResponseEntity.badRequest().body(Map.of("msg", "no_citernes_found"));
        }else {
            return ResponseEntity.ok().body(citerneDtos);
        }
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addCiterne(@Validated @RequestBody CiterneDto citerneDto){
        return ResponseEntity.ok().body(Map.of("msg","citerne_added", "citerne",citerneService.addCiterne(citerneDto)));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getCiterne(@PathVariable Long id){
        return ResponseEntity.ok().body(citerneService.getCiterne(id));
    }

    @PostMapping(value = "/setPompes/{id}", produces = "application/json")
    public ResponseEntity<?> setPompes(@RequestBody List<Long> pompeDtos, @PathVariable Long id){
        CiterneDto citerneDto = citerneService.setPompes(pompeDtos,id);
        if (citerneDto == null){
            return ResponseEntity.badRequest().body(Map.of("msg","pompes_not_set"));
        }else{
            return ResponseEntity.ok().body(Map.of("citerne",citerneDto));
        }
    }

}
