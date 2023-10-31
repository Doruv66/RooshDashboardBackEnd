package com.rooshdashboard.rooshdashboard.business.IParkingGarage;

import com.rooshdashboard.rooshdashboard.domain.ParkingGaragee.CreateParkingGarageRequest;
import com.rooshdashboard.rooshdashboard.domain.ParkingGaragee.CreateParkingGarageResponse;

public interface CreateParkingGarageUseCase {
    CreateParkingGarageResponse CreateParkingGarage(CreateParkingGarageRequest request);
}
