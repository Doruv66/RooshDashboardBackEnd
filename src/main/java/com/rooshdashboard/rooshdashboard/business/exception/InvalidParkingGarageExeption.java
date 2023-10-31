package com.rooshdashboard.rooshdashboard.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidParkingGarageExeption extends ResponseStatusException {
    public InvalidParkingGarageExeption(String errorCode){super(HttpStatus.BAD_REQUEST, errorCode);}
}
