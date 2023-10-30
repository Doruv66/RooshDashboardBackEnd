package com.rooshdashboard.rooshdashboard.business.impl.car;

import com.rooshdashboard.rooshdashboard.business.UpdateCarUseCase;
import com.rooshdashboard.rooshdashboard.domain.car.UpdateCarRequest;
import com.rooshdashboard.rooshdashboard.domain.car.UpdateCarResponse;
import com.rooshdashboard.rooshdashboard.persistance.CarRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.CarEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateCarUseCaseImpl implements UpdateCarUseCase {
    private CarRepository carRepository;

    @Override
    public UpdateCarResponse updateCar(UpdateCarRequest request){
        if (request.getBrand().isBlank() ||
                request.getModel().isBlank() ||
                request.getLicensePlate().isBlank() ||
                request.getElectric() == null) {
            //throw new InvalidDataException();
        }
        if(carRepository.getCarById(request.getId()) == null){
            //throw new NotFoundException();
        }
        CarEntity CarEntity = carRepository.getCarById(request.getId());
        long updatedCarId = updateFields(request, CarEntity);
        return UpdateCarResponse.builder()
                .id(updatedCarId)
                .build();
    }
    private Long updateFields(UpdateCarRequest request, CarEntity Car){
        Car.setId(request.getId());
        Car.setBrand(request.getBrand());
        Car.setModel(request.getModel());
        Car.setLicensePlate(request.getLicensePlate());
        Car.setElectric(request.getElectric());
        return carRepository.saveCar(Car);
    }
}
