package com.rooshdashboard.rooshdashboard.domain.ParkingGarage;

import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageUtilityEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateParkingGarageRequest {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String airport;
    @NotBlank
    private String location;
//    @NotNull
//    private Long accountId;
    @NotNull
    private Long travelTime;
    @NotNull
    private Long travelDistance;
    @NotBlank
    private String phoneNumber;
    @NotNull
    private ParkingGarageUtilityEntity parkingGarageUtility;
    @NotNull
    private List<String> imagePaths;
}
