package com.rooshdashboard.rooshdashboard.business.impl.car;

import com.rooshdashboard.rooshdashboard.business.UpdateCarUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidCarException;
import com.rooshdashboard.rooshdashboard.domain.car.UpdateCarRequest;
import com.rooshdashboard.rooshdashboard.domain.car.UpdateCarResponse;
import com.rooshdashboard.rooshdashboard.persistance.CarRepository;
import com.rooshdashboard.rooshdashboard.persistance.CustomerRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.CarEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateCarUseCaseImpl implements UpdateCarUseCase {
    private CarRepository carRepository;
    private CustomerRepository customerRepository;

    @Override
    public UpdateCarResponse updateCar(UpdateCarRequest request){
        if (!carRepository.existsById(request.getId())) {
            throw new InvalidCarException("CAR_NOT_FOUND");
        }
        CarEntity CarEntity = carRepository.findById(request.getId()).get();
        long updatedCarId = updateFields(request, CarEntity);
        return UpdateCarResponse.builder()
                .id(updatedCarId)
                .build();
    }
    private Long updateFields(UpdateCarRequest request, CarEntity car){
        car.setId(request.getId());
        car.setCustomer(customerRepository.getReferenceById(request.getCustomerId()));
        car.setBrand(request.getBrand());
        car.setModel(request.getModel());
        car.setLicensePlate(request.getLicensePlate());
        car.setElectric(request.getElectric());
        carRepository.save(car);
        return car.getId();
    }
}
