//package com.rooshdashboard.rooshdashboard.controller;
//
//import com.rooshdashboard.rooshdashboard.business.LoginUseCase;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//public class LoginController {
//    private final LoginUseCase loginUseCase;
//
//    @PostMapping
//    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
//        LoginResponse loginResponse = loginUseCase.login(loginRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
//    }
//}
