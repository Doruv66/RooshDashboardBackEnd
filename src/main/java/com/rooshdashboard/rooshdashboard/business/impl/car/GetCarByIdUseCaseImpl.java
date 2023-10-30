package com.rooshdashboard.rooshdashboard.business.impl.car;

import com.rooshdashboard.rooshdashboard.business.GetCarByIdUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidCarException;
import com.rooshdashboard.rooshdashboard.domain.car.Car;
import com.rooshdashboard.rooshdashboard.domain.car.GetCarByIdResponse;
import com.rooshdashboard.rooshdashboard.persistance.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class GetCarByIdUseCaseImpl implements GetCarByIdUseCase {
    private CarRepository carRepository;

    @Override
    public GetCarByIdResponse getCar(long carId){
        if(carRepository.existsById(carId)){
            throw new InvalidCarException("CAR_NOT_FOUND");
        }
        Car car = carRepository.findById(carId).map(CarConverter::convert).get();
        return GetCarByIdResponse.builder().car(car).build();
    }
}
