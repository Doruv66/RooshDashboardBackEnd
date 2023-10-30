package com.rooshdashboard.rooshdashboard.business.impl.location;

import com.rooshdashboard.rooshdashboard.business.GetAllLocationsUseCase;
import com.rooshdashboard.rooshdashboard.domain.location.GetAllLocationsResponse;
import com.rooshdashboard.rooshdashboard.domain.location.Location;
import com.rooshdashboard.rooshdashboard.persistance.LocationRepository;
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
