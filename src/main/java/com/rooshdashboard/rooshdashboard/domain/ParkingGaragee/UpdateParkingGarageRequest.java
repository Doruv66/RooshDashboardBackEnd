package com.rooshdashboard.rooshdashboard.domain.ParkingGaragee;

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
public class UpdateParkingGarageRequest {
    private Integer id;
    @NotBlank
    private String Address;
    @NotNull
    private Integer bookingId;
}
