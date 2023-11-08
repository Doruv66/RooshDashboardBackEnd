package com.rooshdashboard.rooshdashboard.business.impl.ParkingGarage;

import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.ParkingGarage;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.ParkingGarageUtility;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageUtilityEntity;

public class ParkingGarageUtilConverter {
    private ParkingGarageUtilConverter() {

    }

    public static ParkingGarageUtility convert(ParkingGarageUtilityEntity parkingGarageUtil) {
        return ParkingGarageUtility.builder()
                .id(parkingGarageUtil.getId())
                .parkingSpaces(parkingGarageUtil.getParkingSpaces())
                .electricChargePoint(parkingGarageUtil.getElectricChargePoint())
                .floors(parkingGarageUtil.getFloors())
                .toilet(parkingGarageUtil.getToilet())
                .build();
    }
}
