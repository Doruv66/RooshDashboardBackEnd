package com.rooshdashboard.rooshdashboard.domain.ParkingGaragee;

import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageUtilityEntity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateParkingGarageRequest {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String airport;
    @NotBlank
    private String location;
    @NotNull
    private int travelTime;
    @NotNull
    private int travelDistance;
    @NotNull
    private int phoneNumber;
    @NotNull
    private Integer bookingId;
    @NotNull
    private ParkingGarageUtilityEntity parkingGarageUtility;
}
