package com.rooshdashboard.rooshdashboard.business.impl.car;

import com.rooshdashboard.rooshdashboard.business.DeleteCarUseCase;
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
        if(carRepository.getCarById(CarId) == null){
            //throw new NotFoundException();
        }
        Long deletedCarId = carRepository.deleteById(CarId);
        return DeleteCarResponse.builder()
                .id(deletedCarId)
                .build();
    }
}
