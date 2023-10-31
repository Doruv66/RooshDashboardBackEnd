package com.rooshdashboard.rooshdashboard.domain.ParkingGaragee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingGarage {
    private Integer id;
    private String Address;
    private Integer bookingId;

}
