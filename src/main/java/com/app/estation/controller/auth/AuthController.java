package com.app.estation.controller.auth;

import com.app.estation.advice.validation.InsertValidation;
import com.app.estation.dto.AuthDto;
import com.app.estation.dto.UserDto;
import com.app.estation.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<AuthDto> login(@RequestBody UserDto userDto){
        return ResponseEntity.ok(authService.login(userDto));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthDto> register(@Validated(InsertValidation.class) @RequestBody UserDto userDto){
        return ResponseEntity.ok(authService.register(userDto));
    }


    @GetMapping("/test")
    public String test(){
        return "Salam!";
    }

}
