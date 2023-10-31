package com.rooshdashboard.rooshdashboard.business.impl.location;

import com.rooshdashboard.rooshdashboard.domain.location.Location;
import com.rooshdashboard.rooshdashboard.persistance.entity.LocationEntity;

public class LocationConverter {
    private LocationConverter(){}

    public static Location convert(LocationEntity location){
        return Location.builder()
                .id(location.getId())
                .parkingSlot(location.getParkingSlot())
                .floor(location.getFloor())
                .build();
    }
}
