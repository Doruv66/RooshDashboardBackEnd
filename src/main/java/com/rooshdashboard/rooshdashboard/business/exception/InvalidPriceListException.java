package com.rooshdashboard.rooshdashboard.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidPriceListException extends ResponseStatusException {
    public InvalidPriceListException(String errorCode) { super(HttpStatus.BAD_REQUEST, errorCode); }
}