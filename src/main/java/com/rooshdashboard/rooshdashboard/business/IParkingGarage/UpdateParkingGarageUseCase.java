package com.rooshdashboard.rooshdashboard.business.IParkingGarage;

import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.UpdateParkingGarageRequest;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.UpdateParkingGarageResponse;

public interface UpdateParkingGarageUseCase {
    UpdateParkingGarageResponse updateParkingGarage(UpdateParkingGarageRequest request);
}
