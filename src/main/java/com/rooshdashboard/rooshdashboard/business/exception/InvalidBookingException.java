package com.rooshdashboard.rooshdashboard.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidBookingException extends ResponseStatusException {
    public InvalidBookingException(String errorCode){super(HttpStatus.BAD_REQUEST, errorCode);}
}
