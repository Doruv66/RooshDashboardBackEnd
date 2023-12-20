package com.rooshdashboard.rooshdashboard.business.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class InvalidDataException extends RuntimeException {
    private final Map<String, String> validationErrors;
    public InvalidDataException(Map<String, String> validationErrors) {
        super("Validation failed");
        this.validationErrors = validationErrors;
    }
}
