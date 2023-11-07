package com.rooshdashboard.rooshdashboard.business.IParkingGarage;

import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.ParkingGarage;

import java.util.Optional;

public interface GetParkingGarageByIdUseCase {
    Optional<ParkingGarage> getParkingGarageById(long id);
}
