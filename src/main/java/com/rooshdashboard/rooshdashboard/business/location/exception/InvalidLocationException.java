package com.rooshdashboard.rooshdashboard.business.location.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidLocationException extends ResponseStatusException {
    public InvalidLocationException(String errorCode){
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
