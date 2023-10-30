package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.car.GetCarByIdResponse;

public interface GetCarByIdUseCase {
    GetCarByIdResponse getCar(long carId);
}
