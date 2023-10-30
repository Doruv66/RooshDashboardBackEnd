package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.car.UpdateCarRequest;
import com.rooshdashboard.rooshdashboard.domain.car.UpdateCarResponse;

public interface UpdateCarUseCase {
    UpdateCarResponse updateCar(UpdateCarRequest request);
}
