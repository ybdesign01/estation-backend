package com.app.estation.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApiRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ApiRequestException(String message) {
        super(message);
    }
}
