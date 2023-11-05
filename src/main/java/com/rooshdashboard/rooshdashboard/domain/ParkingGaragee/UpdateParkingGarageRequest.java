package com.rooshdashboard.rooshdashboard.domain.ParkingGaragee;

import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageUtilityEntity;
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
    private Long id;
    @NotBlank
    private String Address;
    @NotNull
    private Integer bookingId;
    @NotNull
    private ParkingGarageUtilityEntity parkingGarageUtility;
}
