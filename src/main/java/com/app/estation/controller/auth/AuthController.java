package com.app.estation.controller.auth;

import com.app.estation.dto.AuthDto;
import com.app.estation.dto.LoginDto;
import com.app.estation.dto.RefreshTokenRequest;
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

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<AuthDto> login(@RequestBody LoginDto loginDto){
        AuthDto dto = authService.login(loginDto);
        if (null == dto.getToken()){
            return ResponseEntity.status(400).body(dto);
        }else{
            return ResponseEntity.ok(dto);
        }
    }

    @PostMapping(value = "/refresh", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest dto){
        return ResponseEntity.ok(authService.refreshToken(dto));
    }


}
