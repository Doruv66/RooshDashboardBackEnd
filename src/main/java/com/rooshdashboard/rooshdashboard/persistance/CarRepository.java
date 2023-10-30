package com.rooshdashboard.rooshdashboard.persistance;

import com.rooshdashboard.rooshdashboard.persistance.entity.CarEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarRepository {
    Long saveCar(CarEntity Car);
    Long deleteById(long carId);
    List<CarEntity> getAllCars();
    CarEntity getCarById(long carId);
}