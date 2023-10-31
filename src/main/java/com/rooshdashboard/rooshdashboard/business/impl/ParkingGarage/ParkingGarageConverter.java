package com.rooshdashboard.rooshdashboard.business.impl.ParkingGarage;

import com.rooshdashboard.rooshdashboard.domain.ParkingGaragee.ParkingGarage;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;

final class ParkingGarageConverter {
    private ParkingGarageConverter() {

    }

    public static ParkingGarage convert(ParkingGarageEntity parkingGarage) {
        return ParkingGarage.builder().id(parkingGarage.getId()).bookingId(parkingGarage.getBookingId()).Address(parkingGarage.getAddress()).build();
    }
}
