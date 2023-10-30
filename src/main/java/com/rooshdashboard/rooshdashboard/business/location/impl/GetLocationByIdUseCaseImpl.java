package com.rooshdashboard.rooshdashboard.business.location.impl;

import com.rooshdashboard.rooshdashboard.business.location.GetLocationByIdUseCase;
import com.rooshdashboard.rooshdashboard.domain.location.Location;
import com.rooshdashboard.rooshdashboard.persistance.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class GetLocationByIdUseCaseImpl implements GetLocationByIdUseCase {
    private final LocationRepository locationRepository;
    @Override
    public Optional<Location> getLocation(long locationId) {
        return locationRepository.findById(locationId).map(LocationConverter::convert);
    }
}
