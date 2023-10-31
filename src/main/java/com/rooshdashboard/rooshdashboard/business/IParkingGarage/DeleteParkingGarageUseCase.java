package com.rooshdashboard.rooshdashboard.business.IParkingGarage;

import com.rooshdashboard.rooshdashboard.domain.ParkingGaragee.DeleteParkingGarageResponse;

public interface DeleteParkingGarageUseCase {
    DeleteParkingGarageResponse deleteParkingGarage(int id);
}
