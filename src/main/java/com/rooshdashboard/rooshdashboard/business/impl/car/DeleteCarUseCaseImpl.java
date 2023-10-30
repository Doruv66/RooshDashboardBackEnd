package com.rooshdashboard.rooshdashboard.business.impl.car;

import com.rooshdashboard.rooshdashboard.business.DeleteCarUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidCarException;
import com.rooshdashboard.rooshdashboard.domain.car.DeleteCarResponse;
import com.rooshdashboard.rooshdashboard.persistance.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteCarUseCaseImpl implements DeleteCarUseCase {
    private final CarRepository carRepository;

    @Override
    public DeleteCarResponse deleteCar(long CarId) {
        if(!carRepository.existsById(CarId)){
            throw new InvalidCarException("CAR_NOT_FOUND");
        }
        carRepository.deleteById(CarId);
        return DeleteCarResponse.builder()
                .id(CarId)
                .build();
    }
}
