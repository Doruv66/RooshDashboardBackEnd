package com.rooshdashboard.rooshdashboard.business.impl.location;

import com.rooshdashboard.rooshdashboard.business.GetLocationByIdUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidLocationException;
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
        if(!locationRepository.existsById(locationId)){
            throw new InvalidLocationException("LOCATION_ID_INVALID");
        }
        return locationRepository.findById(locationId).map(LocationConverter::convert);
    }
}
