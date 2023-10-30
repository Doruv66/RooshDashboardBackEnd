package com.rooshdashboard.rooshdashboard.business.location.impl;

import com.rooshdashboard.rooshdashboard.business.location.GetAllLocationsUseCase;
import com.rooshdashboard.rooshdashboard.domain.location.GetAllLocationsResponse;
import com.rooshdashboard.rooshdashboard.domain.location.Location;
import com.rooshdashboard.rooshdashboard.persistance.LocationRepository;
import com.rooshdashboard.rooshdashboard.persistance.impl.FakeLocationRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class GetAllLocationsUseCaseImpl implements GetAllLocationsUseCase {
    private final LocationRepository locationRepository;
    @Override
    public GetAllLocationsResponse getLocations() {
        List<Location> locations = locationRepository.findAll()
                .stream()
                .map(LocationConverter::convert)
                .toList();
        return GetAllLocationsResponse.builder()
                .locationsList(locations)
                .build();
    }
}
