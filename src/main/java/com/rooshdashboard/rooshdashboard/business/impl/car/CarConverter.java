package com.rooshdashboard.rooshdashboard.business.impl.car;

import com.rooshdashboard.rooshdashboard.business.impl.Customer.CustomerConverter;
import com.rooshdashboard.rooshdashboard.domain.Customer.Customer;
import com.rooshdashboard.rooshdashboard.domain.car.Car;
import com.rooshdashboard.rooshdashboard.persistance.entity.CarEntity;

public class CarConverter {
    private CarConverter() {
    }

    public static Car convert(CarEntity car) {
        Customer convertedCustomer = CustomerConverter.convert(car.getCustomer());
        return Car.builder()
                .id(car.getId())
                .customer(convertedCustomer)
                .model(car.getModel())
                .brand(car.getBrand())
                .licensePlate(car.getLicensePlate())
                .electric(car.getElectric())
                .build();
    }
}
