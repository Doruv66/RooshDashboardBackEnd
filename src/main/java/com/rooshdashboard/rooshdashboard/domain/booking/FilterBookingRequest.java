package com.rooshdashboard.rooshdashboard.domain.booking;

import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.ParkingGarage;
import com.rooshdashboard.rooshdashboard.domain.service.Service;
import com.rooshdashboard.rooshdashboard.domain.service.ServiceType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterBookingRequest {
    @NotNull
    private long garageId;
    @NotNull
    private ServiceType service;
    @NotNull
    private boolean finished;
    @NotNull
    private boolean ongoing;
}
