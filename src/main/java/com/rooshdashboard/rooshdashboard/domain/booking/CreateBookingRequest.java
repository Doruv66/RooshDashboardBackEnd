package com.rooshdashboard.rooshdashboard.domain.booking;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingRequest {
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
