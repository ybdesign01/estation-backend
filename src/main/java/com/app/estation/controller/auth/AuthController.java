package com.app.estation.controller.auth;

import com.app.estation.dto.AuthDto;
import com.app.estation.dto.UserDto;
import com.app.estation.service.implementation.AuthService;
import com.app.estation.util.PassEncode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private PassEncode passEncode;

    @PostMapping
    public ResponseEntity<AuthDto> login(@RequestBody UserDto userDto){
        AuthDto dto = authService.login(userDto);
        System.out.println(dto);
        if (dto.getToken() == null){
            return ResponseEntity.status(400).body(dto);
        }else{
            return ResponseEntity.ok(authService.login(userDto));
        }
    }


}
