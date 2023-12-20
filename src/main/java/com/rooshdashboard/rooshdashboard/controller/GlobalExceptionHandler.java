package com.rooshdashboard.rooshdashboard.controller;

import com.rooshdashboard.rooshdashboard.business.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidBookingException.class)
    public ResponseEntity<Map<String, String>> handleInvalidBookingException(InvalidBookingException ex) {
        Map<String, String> responseBody = Collections.singletonMap("error", ex.getReason());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(responseBody, headers, ex.getStatusCode());
    }

    @ExceptionHandler(InvalidCarException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCarException(InvalidCarException ex) {
        Map<String, String> responseBody = Collections.singletonMap("error", ex.getReason());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(responseBody, headers, ex.getStatusCode());
    }

    @ExceptionHandler(InvalidLocationException.class)
    public ResponseEntity<Map<String, String>> handleInvalidLocationException(InvalidLocationException ex) {
        Map<String, String> responseBody = Collections.singletonMap("error", ex.getReason());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(responseBody, headers, ex.getStatusCode());
    }

    @ExceptionHandler(InvalidServiceException.class)
    public ResponseEntity<Map<String, String>> handleInvalidUserActivityLogException(InvalidServiceException ex) {
        Map<String, String> responseBody = Collections.singletonMap("error", ex.getReason());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(responseBody, headers, ex.getStatusCode());
    }

    @ExceptionHandler(InvalidCustomerException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCustomerException(InvalidCustomerException ex) {
        Map<String, String> responseBody = Collections.singletonMap("error", ex.getReason());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(responseBody, headers, ex.getStatusCode());
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<Object> handleInvalidDataException(InvalidDataException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getValidationErrors());
    }
}
