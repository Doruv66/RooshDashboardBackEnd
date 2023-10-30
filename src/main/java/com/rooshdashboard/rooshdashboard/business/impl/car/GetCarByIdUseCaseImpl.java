package com.rooshdashboard.rooshdashboard.business.impl.car;

import com.rooshdashboard.rooshdashboard.business.GetCarByIdUseCase;
import com.rooshdashboard.rooshdashboard.domain.car.Car;
import com.rooshdashboard.rooshdashboard.persistance.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetCarByIdUseCaseImpl implements GetCarByIdUseCase {
    private CarRepository carRepository;

    @Override
    public Car getCar(long carId){
        if(carRepository.getCarById(carId) == null){
            //throw new NotFoundException();
        }
        return CarConverter.convert(carRepository.getCarById(carId));
    }
}
