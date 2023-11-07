package com.rooshdashboard.rooshdashboard.business.impl.car;

import com.rooshdashboard.rooshdashboard.business.CreateCarUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidCarException;
import com.rooshdashboard.rooshdashboard.domain.car.CreateCarRequest;
import com.rooshdashboard.rooshdashboard.domain.car.CreateCarResponse;
import com.rooshdashboard.rooshdashboard.persistance.CarRepository;
import com.rooshdashboard.rooshdashboard.persistance.CustomerRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.CarEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCarUseCaseImpl implements CreateCarUseCase {
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    @Override
    public CreateCarResponse createCar(CreateCarRequest request) {
        if (request ==  null) {
            throw new InvalidCarException("COULD_NOT_CREATE_CAR");
        }
        Long savedCarId = saveNewCar(request);

        return CreateCarResponse.builder()
                .id(savedCarId)
                .build();
    }

    private Long saveNewCar(CreateCarRequest request) {

        CarEntity newCar = CarEntity.builder()
                .customer(customerRepository.getReferenceById(request.getCustomerId()))
                .model(request.getModel())
                .electric(request.getElectric())
                .brand(request.getBrand())
                .licensePlate(request.getLicensePlate())
                .build();
        long newCarId = carRepository.save(newCar).getId();
        return newCarId;
    }
}
