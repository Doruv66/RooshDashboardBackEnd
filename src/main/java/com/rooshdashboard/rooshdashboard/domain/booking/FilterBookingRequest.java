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
    private ServiceType service;
    private boolean finished;
    private boolean ongoing;
}
