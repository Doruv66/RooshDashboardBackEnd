package com.rooshdashboard.rooshdashboard.persistance.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingGarageEntity {
    private Integer id;
    private String Address;
    private Integer bookingId;
}
