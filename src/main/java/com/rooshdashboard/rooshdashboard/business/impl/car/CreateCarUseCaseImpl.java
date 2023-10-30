package com.rooshdashboard.rooshdashboard.business.impl.car;

import com.rooshdashboard.rooshdashboard.business.CreateCarUseCase;
import com.rooshdashboard.rooshdashboard.domain.car.CreateCarRequest;
import com.rooshdashboard.rooshdashboard.domain.car.CreateCarResponse;
import com.rooshdashboard.rooshdashboard.persistance.CarRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.CarEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCarUseCaseImpl implements CreateCarUseCase {
    private final CarRepository carRepository;

    @Override
    public CreateCarResponse createCar(CreateCarRequest request) {
        if (request.getBrand().isBlank() ||
                request.getModel().isBlank() ||
                request.getLicensePlate().isBlank() ||
                request.getElectric() == null) {
            //throw new InvalidDataException();
        }
        Long savedCarId = saveNewCar(request);

        return CreateCarResponse.builder()
                .id(savedCarId)
                .build();
    }

    private Long saveNewCar(CreateCarRequest request) {

        CarEntity newCar = CarEntity.builder()
                .model(request.getModel())
                .electric(request.getElectric())
                .brand(request.getBrand())
                .licensePlate(request.getLicensePlate())
                .build();
        return carRepository.saveCar(newCar);
    }
}
