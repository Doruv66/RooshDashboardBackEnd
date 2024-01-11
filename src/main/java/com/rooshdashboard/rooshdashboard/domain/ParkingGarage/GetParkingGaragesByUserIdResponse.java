package com.rooshdashboard.rooshdashboard.domain.ParkingGarage;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetParkingGaragesByUserIdResponse {
    List<ParkingGarage> parkingGarages;
}
