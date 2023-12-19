package com.rooshdashboard.rooshdashboard.persistance;

import com.rooshdashboard.rooshdashboard.domain.PriceList.PriceList;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;
import com.rooshdashboard.rooshdashboard.persistance.entity.PriceListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceListRepository extends JpaRepository<PriceListEntity, Long> {
    List<PriceListEntity> findByGarage(ParkingGarageEntity garage);
}
