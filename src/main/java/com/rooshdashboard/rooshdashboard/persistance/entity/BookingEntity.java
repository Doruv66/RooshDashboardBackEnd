package com.rooshdashboard.rooshdashboard.persistance.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookingEntity {
    private Long id;
    private Long customerId;
    private Long carId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long flightNumberDeparture;
    private Long flightNumberArrival;
    private Long locationId;
    private Long serviceId;
}
