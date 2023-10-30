package com.rooshdashboard.rooshdashboard.domain.booking;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Booking {
    @NotNull
    private Long id;

    @NotNull
    private Long customerId;

    @NotNull
    private Long carId;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    private Long flightNumberDeparture;

    @NotNull
    private Long flightNumberArrival;

    @NotNull
    private Long locationId;

    @NotNull
    private Long serviceId;
}
