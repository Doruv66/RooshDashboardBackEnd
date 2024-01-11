package com.rooshdashboard.rooshdashboard.business.IParkingGarage;

import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.GetParkingGaragesByUserIdResponse;

public interface GetParkingGaragesByUserIdUseCase {
   public GetParkingGaragesByUserIdResponse getParkingGaragesByUserId (Long userId);
}
