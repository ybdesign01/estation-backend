package com.app.estation.controller;


import com.app.estation.advice.validation.InsertValidation;
import com.app.estation.dto.User.*;
import com.app.estation.service.implementation.PompeServiceImpl;
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

import java.util.List;
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAll(){
        List<UserDto> users = userService.getAll();
        if(null != users) {
            return ResponseEntity.ok().body(users);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg","no_users_found"));
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable Long id){
        UserDto user = userService.getUser(id);
        if(null != user){
            return ResponseEntity.ok().body(user);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg","user_not_found"));
        }
    }

    @GetMapping(value = "/getUser", produces = "application/json")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getByToken(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token){
        UserDto user = userService.getUserByToken(token);
        if(null != user){
            return ResponseEntity.ok().body(user);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg","user_not_found"));
        }
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addUser(@Validated(InsertValidation.class) @RequestBody UserPassDto userDto){
        UserDto user = userService.addUser(userDto);
        if(null == user){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg","user_exists"));
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateUser(@Validated(InsertValidation.class) @RequestBody UserPassDto userDto, @PathVariable Long id){
        UserDto user = userService.updateUser(id, userDto);
        if (null == user) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg", "user_not_updated"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
    }

    @DeleteMapping (value = "/{id}", produces = "application/json")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        if(userService.deleteUser(id)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(Map.of("msg","user_deleted"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg","user_not_found"));
        }
    }

    @GetMapping(value = "/getStation/{id}", produces = "application/json")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getStation(@PathVariable final Long id){
        List<StationUserDto> stations = stationUserService.getAllStationsByUser(id);
        if(null != stations){
            return ResponseEntity.ok().body(stations);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg","no_station_found"));
        }
    }

    @PostMapping(value = "/setStation", produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasAuthority({'ADMIN', 'MANAGER'})")
    public ResponseEntity<?> setStation(@Validated @RequestBody final StationUserDto stationUserDto){
        StationUserDto station = stationUserService.addStationUser(stationUserDto);
        if(null != station){
            return ResponseEntity.status(HttpStatus.CREATED).body(station);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg","station_user_exists"));
        }
    }

    @PostMapping(value = "/updateStation", produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasAuthority({'ADMIN', 'MANAGER'})")
    public ResponseEntity<?> updateStation(@Validated @RequestBody final StationUserDto stationUserDto){
        StationUserDto station = stationUserService.updateStationUser(stationUserDto);
        if(null != station){
            return ResponseEntity.status(HttpStatus.OK).body(station);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg","station_user_not_found"));
        }
    }

    @PostMapping(value = "/setPompe", produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasAuthority({'ADMIN', 'MANAGER'})")
    public ResponseEntity<?> setPompe(@Validated @RequestBody final PompeUserDto pompeUserKeyDto){
        PompeUserDto pompeUserDto = pompeUserService.addPompeUser(pompeUserKeyDto);
        if(null != pompeUserDto){
            return ResponseEntity.status(HttpStatus.CREATED).body(pompeUserDto);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg","pompe_user_exists"));
        }
    }

    @GetMapping(value = "/getPompes/{id}", produces = "application/json")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getPompe(@PathVariable final Long id){
        List<PompeUserDto> pompes = pompeUserService.getAllPompesByUser(id);
        if(null != pompes){
            return ResponseEntity.ok().body(pompes);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg","no_pompe_found"));
        }
    }


    @GetMapping(value = "/getStationUser", produces = "application/json")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getStationUser(){
        return ResponseEntity.ok().body(stationUserService.getAll());
    }






}
