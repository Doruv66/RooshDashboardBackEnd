package com.rooshdashboard.rooshdashboard.persistance.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarEntity {
    private Long id;
    private String licensePlate;
    private String brand;
    private String model;
    private Boolean electric;
}


