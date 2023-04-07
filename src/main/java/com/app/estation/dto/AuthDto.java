package com.app.estation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "builder")
@NoArgsConstructor
public class AuthDto {
    private String token;
    private String msg;
}
