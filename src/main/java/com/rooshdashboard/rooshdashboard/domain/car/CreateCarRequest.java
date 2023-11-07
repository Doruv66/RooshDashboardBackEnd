package com.rooshdashboard.rooshdashboard.domain.car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {
    @NotBlank
    private String licensePlate;
    @NotNull
    private Long customerId;
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    @NotNull
    private Boolean electric;
}
