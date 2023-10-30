package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.car.CreateCarRequest;
import com.rooshdashboard.rooshdashboard.domain.car.CreateCarResponse;

public interface CreateCarUseCase {
    CreateCarResponse createCar(CreateCarRequest request);
}
