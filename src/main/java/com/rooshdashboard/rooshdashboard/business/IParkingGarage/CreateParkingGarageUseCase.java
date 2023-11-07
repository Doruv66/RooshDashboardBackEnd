package com.rooshdashboard.rooshdashboard.business.IParkingGarage;

import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.CreateParkingGarageRequest;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.CreateParkingGarageResponse;

public interface CreateParkingGarageUseCase {
    CreateParkingGarageResponse CreateParkingGarage(CreateParkingGarageRequest request);
}
