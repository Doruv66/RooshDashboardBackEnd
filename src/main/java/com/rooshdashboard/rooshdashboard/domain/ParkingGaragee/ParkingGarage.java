package com.rooshdashboard.rooshdashboard.domain.ParkingGaragee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingGarage {
    private Long id;
    private String Address;
    private List<Long> bookingIds;
}
