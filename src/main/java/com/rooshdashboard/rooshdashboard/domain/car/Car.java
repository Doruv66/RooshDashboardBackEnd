package com.rooshdashboard.rooshdashboard.domain.car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Car {
    @NotNull
    private Long id;
    @NotBlank
    private String licensePlate;
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    @NotNull
    private Boolean electric;
}

