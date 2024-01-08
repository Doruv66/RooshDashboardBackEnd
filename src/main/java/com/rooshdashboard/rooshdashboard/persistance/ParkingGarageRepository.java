package com.rooshdashboard.rooshdashboard.persistance;

import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingGarageRepository extends JpaRepository<ParkingGarageEntity, Long> {
    List<ParkingGarageEntity> findByAccount_Id(Long accountId);
}
