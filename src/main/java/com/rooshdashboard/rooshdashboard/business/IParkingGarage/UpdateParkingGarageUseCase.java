package com.rooshdashboard.rooshdashboard.business.IParkingGarage;

import com.rooshdashboard.rooshdashboard.business.impl.ParkingGarage.UpdateParkingGarageUseCaseImpl;
import com.rooshdashboard.rooshdashboard.domain.ParkingGaragee.UpdateParkingGarageRequest;
import com.rooshdashboard.rooshdashboard.domain.ParkingGaragee.UpdateParkingGarageResponse;

public interface UpdateParkingGarageUseCase {
    UpdateParkingGarageResponse updateParkingGarage(UpdateParkingGarageRequest request);
}
