package com.rooshdashboard.rooshdashboard.business.IParkingGarage;

import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.DeleteParkingGarageResponse;

public interface DeleteParkingGarageUseCase {
    DeleteParkingGarageResponse deleteParkingGarage(Long id);
}
