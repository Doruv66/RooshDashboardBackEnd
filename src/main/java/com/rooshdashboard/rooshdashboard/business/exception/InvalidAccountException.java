package com.rooshdashboard.rooshdashboard.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidAccountException extends ResponseStatusException {
    public InvalidAccountException(String errorCode) { super(HttpStatus.BAD_REQUEST, errorCode); }
}
