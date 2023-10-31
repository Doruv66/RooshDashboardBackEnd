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
public class UpdateCarRequest {
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