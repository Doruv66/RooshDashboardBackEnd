package com.rooshdashboard.rooshdashboard.domain.car;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Car {
    private Long id;
    private String licensePlate;
    private String brand;
    private String model;
    private Boolean electric;
}

