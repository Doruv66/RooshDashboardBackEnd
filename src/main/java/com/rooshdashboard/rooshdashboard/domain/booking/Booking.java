package com.rooshdashboard.rooshdashboard.domain.booking;

import com.rooshdashboard.rooshdashboard.domain.Customer.Customer;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.ParkingGarage;
import com.rooshdashboard.rooshdashboard.domain.car.Car;
import com.rooshdashboard.rooshdashboard.domain.service.Service;
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
public class Booking {
    @NotNull
    private Long id;

    @NotNull
    private Customer customerId;

    @NotNull
    private Car carId;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    private Long flightNumberDeparture;

    @NotNull
    private Long flightNumberArrival;
    @NotNull
    private ParkingGarage garageId;
    @NotNull
    private Service serviceId;
}
