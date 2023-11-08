package com.rooshdashboard.rooshdashboard.persistance;

import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageUtilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingGarageUtilityRepository extends JpaRepository<ParkingGarageUtilityEntity, Long> {
}
