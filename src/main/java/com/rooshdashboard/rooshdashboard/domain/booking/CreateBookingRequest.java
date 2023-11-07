package com.rooshdashboard.rooshdashboard.domain.booking;

import com.rooshdashboard.rooshdashboard.persistance.entity.CarEntity;
import com.rooshdashboard.rooshdashboard.persistance.entity.CustomerEntity;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;
import com.rooshdashboard.rooshdashboard.persistance.entity.ServiceEntity;
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

    private Long id;

    @NotNull
    private CustomerEntity customer;

    @NotNull
    private CarEntity car;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    private Long flightNumberDeparture;

    @NotNull
    private Long flightNumberArrival;

    @NotNull
    private ParkingGarageEntity garage;

    @NotNull
    private ServiceEntity service;
}
