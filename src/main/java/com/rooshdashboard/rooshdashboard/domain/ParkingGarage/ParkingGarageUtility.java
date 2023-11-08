package com.rooshdashboard.rooshdashboard.domain.ParkingGarage;

import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingGarageUtility {
    private Long id;
    private Boolean toilet;
    private Boolean electricChargePoint;
    private Long floors;
    private Long parkingSpaces;
    private Long parkingSpacesElectric;
}
