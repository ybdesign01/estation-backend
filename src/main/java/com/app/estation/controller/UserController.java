package com.app.estation.controller;


import com.app.estation.advice.validation.InsertValidation;
import com.app.estation.dto.UserDto;
import com.app.estation.dto.UserPassDto;
import com.app.estation.entity.User;
import com.app.estation.repository.ProfileRepository;
import com.app.estation.repository.UserRepository;
import com.app.estation.service.implementation.UserServiceImpl;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        User u = userService.getUser(id);
        if(u != null){
            UserDto user = modelMapper.map(u, UserDto.class);
            return ResponseEntity.ok().body(user);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg","User not found"));
        }
    }

    @GetMapping("/getUser")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserPassDto> getByToken(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token){
        User u = userService.getUserByToken(token);
        if(u != null){
            UserPassDto user = modelMapper.map(u, UserPassDto.class);
            return ResponseEntity.ok().body(user);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> addUser(@Validated(InsertValidation.class) @RequestBody UserDto userDto){
        User user = userService.addUser(userDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> updateUser(@Validated(InsertValidation.class) @RequestBody UserDto userDto, @PathVariable Long id){
        User user = userService.updateUser(id, userDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
    }

    @DeleteMapping ("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
        if(userService.deleteUser(id)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(Map.of("msg","User deleted"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg","User not found"));
        }
    }



}
