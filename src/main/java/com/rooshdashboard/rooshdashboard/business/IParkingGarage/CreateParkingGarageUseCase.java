package com.rooshdashboard.rooshdashboard.business.IParkingGarage;

import com.rooshdashboard.rooshdashboard.domain.ParkingGaragee.CreateParkingGarageRequest;
import com.rooshdashboard.rooshdashboard.domain.ParkingGaragee.CreateParkingGarageResponse;
import com.rooshdashboard.rooshdashboard.domain.ParkingGaragee.GetParkingGarageResponse;

public interface CreateParkingGarageUseCase {
    CreateParkingGarageResponse CreateParkingGarage(CreateParkingGarageRequest request);
}
