package com.app.estation.advice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class ApiRequestOkException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ApiRequestOkException(String message) {
        super(message);
    }
}

