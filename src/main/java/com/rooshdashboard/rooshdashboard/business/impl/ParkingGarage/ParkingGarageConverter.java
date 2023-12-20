package com.rooshdashboard.rooshdashboard.business.impl.ParkingGarage;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.ParkingGarage;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;

public class ParkingGarageConverter {
    private ParkingGarageConverter() {

    }

    public static ParkingGarage convert(ParkingGarageEntity parkingGarage) {
        return ParkingGarage.builder()
                .id(parkingGarage.getId())
                .location(parkingGarage.getLocation())
                .parkingGarageUtility(ParkingGarageUtilConverter.convert(parkingGarage.getParkingGarageUtility()))
                .phoneNumber(parkingGarage.getPhoneNumber())
                .airport(parkingGarage.getAirport())
                .travelTime(parkingGarage.getTravelTime())
//                .account(AccountConverter.convert(parkingGarage.getAccount()))
                .name(parkingGarage.getName())
                .travelDistance(parkingGarage.getTravelDistance())
                .build();
    }
}
