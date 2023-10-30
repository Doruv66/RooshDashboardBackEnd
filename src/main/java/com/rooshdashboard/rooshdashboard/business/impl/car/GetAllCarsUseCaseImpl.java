package com.rooshdashboard.rooshdashboard.business.impl.car;

import com.rooshdashboard.rooshdashboard.business.GetAllCarsUseCase;
import com.rooshdashboard.rooshdashboard.domain.car.Car;
import com.rooshdashboard.rooshdashboard.domain.car.GetAllCarsResponse;
import com.rooshdashboard.rooshdashboard.persistance.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllCarsUseCaseImpl implements GetAllCarsUseCase {
    private final CarRepository carRepository;

    @Override
    public GetAllCarsResponse getCars() {
        List<Car> Cars = carRepository.findAll()
                .stream()
                .map(CarConverter::convert)
                .toList();

        return GetAllCarsResponse.builder()
                .cars(Cars)
                .build();
    }
}
