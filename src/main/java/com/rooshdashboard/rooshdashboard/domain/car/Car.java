package com.rooshdashboard.rooshdashboard.domain.car;

import com.rooshdashboard.rooshdashboard.domain.Customer.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Car {
    @NotNull
    private Long id;
    @NotNull
    private Customer customer;
    @NotBlank
    private String licensePlate;
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    @NotNull
    private Boolean electric;
}

