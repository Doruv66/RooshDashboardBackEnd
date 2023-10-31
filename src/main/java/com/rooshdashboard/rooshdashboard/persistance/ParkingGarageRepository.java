package com.rooshdashboard.rooshdashboard.persistance;

import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ParkingGarageRepository {
    boolean existsById(int id);

    void deleteById(int id);
    ParkingGarageEntity save(ParkingGarageEntity parkingGarage);
    List<ParkingGarageEntity>  findAll();
    Optional<ParkingGarageEntity> findById(int id);
}
