package com.rooshdashboard.rooshdashboard.business.impl.ParkingGarage;

import com.rooshdashboard.rooshdashboard.domain.ParkingGaragee.ParkingGarage;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;

public class ParkingGarageConverter {
    private ParkingGarageConverter() {

    }

    public static ParkingGarage convert(ParkingGarageEntity parkingGarage) {
        return ParkingGarage.builder().id(parkingGarage.getId()).bookingId(parkingGarage.getBookingId()).location(parkingGarage.getLocation()).build();
    }
}
