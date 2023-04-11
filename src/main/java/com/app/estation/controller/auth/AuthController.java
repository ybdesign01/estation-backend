package com.app.estation.controller.auth;

import com.app.estation.dto.AuthDto;
import com.app.estation.dto.UserDto;
import com.app.estation.service.implementation.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


}
