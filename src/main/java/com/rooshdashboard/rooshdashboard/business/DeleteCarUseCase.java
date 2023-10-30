package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.car.DeleteCarResponse;

public interface DeleteCarUseCase {
    DeleteCarResponse deleteCar(long carId);
}
