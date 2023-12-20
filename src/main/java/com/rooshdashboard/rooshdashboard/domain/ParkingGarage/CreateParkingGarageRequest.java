package com.rooshdashboard.rooshdashboard.domain.ParkingGarage;

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
public class CreateParkingGarageRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String airport;
    @NotBlank
    private String location;
    private Long accountId;
    @NotNull
    private Long travelTime;
    @NotNull
    private Long travelDistance;
    @NotBlank
    private String phoneNumber;
    @NotNull
    private ParkingGarageUtilityEntity parkingGarageUtility;
}
