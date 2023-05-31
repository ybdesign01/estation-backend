package com.app.estation.controller;


import com.app.estation.dto.PompeUserRequest;
import com.app.estation.dto.StationUserRequest;
import com.app.estation.dto.User.StationUserDto;
import com.app.estation.dto.User.UserPassDto;
import com.app.estation.service.implementation.PompeUserServiceImpl;
import com.app.estation.service.implementation.StationUserServiceImpl;
import com.app.estation.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private StationUserServiceImpl stationUserService;

    @Autowired
    private PompeUserServiceImpl pompeUserService;


    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> getAll(){
            return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable Long id){
            return ResponseEntity.ok().body(userService.get(id));
    }

    @GetMapping(value = "/getUser", produces = "application/json")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getByToken(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token){
            return ResponseEntity.ok().body(userService.getUserByToken(token));
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> addUser(@Validated @RequestBody final UserPassDto userDto){
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("msg","user_created", "user", userService.add(userDto)));
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateUser(@Validated @RequestBody UserPassDto userDto, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("msg","user_updated", "user", userService.update(userDto,id)));
    }

    @DeleteMapping (value = "/{id}", produces = "application/json")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        if(!userService.delete(id)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(Map.of("msg","user_deleted"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg","user_not_found"));
        }
    }

    @GetMapping(value = "/getStation/{id}", produces = "application/json")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getStation(@PathVariable final Long id){
        return ResponseEntity.ok().body(stationUserService.getCurrentStation(id));
    }

    @PostMapping(value = "/setStation", produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> setStation(@Validated @RequestBody final StationUserRequest stationUserRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("msg","station_user_created","stationUser",stationUserService.setStationtoUser(stationUserRequest)));
    }

    @PostMapping(value = "/updateStation/{id}", produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateStation(@Validated @RequestBody final StationUserDto stationUserDto, @PathVariable final Long id){
        StationUserDto station = stationUserService.update(stationUserDto,id);
        return ResponseEntity.status(HttpStatus.OK).body(station);
    }

    @PostMapping(value = "/setPompe", produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> setPompe(@Validated @RequestBody PompeUserRequest request){
       return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("msg","pompe_user_created","pompeUser",pompeUserService.setPompeToUser(request)));
    }

    @GetMapping(value = "/getPompes/{id}", produces = "application/json")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getPompe(@PathVariable final Long id){
        return ResponseEntity.ok().body(pompeUserService.getPompsAssignedToUserForToday(id));
    }

    @GetMapping(value = "/getAllPompes/{id}", produces = "application/json")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getAllPompe(@PathVariable final Long id){
        return ResponseEntity.ok().body(pompeUserService.getAllPompesByUser(id));
    }




    @GetMapping(value = "/getStationUser", produces = "application/json")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getStationUser(){
        return ResponseEntity.ok().body(stationUserService.getAll());
    }

    @GetMapping(value = "/getAffectationsMontant/{id}", produces = "application/json")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> getAffectationsMontant(@PathVariable final Long id){
        return ResponseEntity.ok().body(pompeUserService.getAffectationsMontant(id));
    }









}
