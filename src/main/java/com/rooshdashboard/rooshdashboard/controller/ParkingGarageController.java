package com.rooshdashboard.rooshdashboard.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parkinggarage")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")

public class ParkingGarageController {
}
