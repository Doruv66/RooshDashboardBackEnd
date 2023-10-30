package com.rooshdashboard.rooshdashboard.business.impl.car;

import com.rooshdashboard.rooshdashboard.domain.car.Car;
import com.rooshdashboard.rooshdashboard.persistance.entity.CarEntity;

public class CarConverter {
    private CarConverter() {
    }

    public static Car convert(CarEntity car) {
        return Car.builder()
                .id(car.getId())
                .model(car.getModel())
                .brand(car.getBrand())
                .licensePlate(car.getLicensePlate())
                .electric(car.getElectric())
                .build();
    }
}
