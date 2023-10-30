package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.car.Car;

public interface GetCarByIdUseCase {
    Car getCar(long carId);
}
