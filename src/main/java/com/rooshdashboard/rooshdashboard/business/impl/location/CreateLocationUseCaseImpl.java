package com.rooshdashboard.rooshdashboard.business.impl.location;

import com.rooshdashboard.rooshdashboard.business.CreateLocationUseCase;
import com.rooshdashboard.rooshdashboard.domain.location.CreateLocationRequest;
import com.rooshdashboard.rooshdashboard.domain.location.CreateLocationResponse;
import com.rooshdashboard.rooshdashboard.persistance.LocationRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.LocationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateLocationUseCaseImpl implements CreateLocationUseCase {
    private final LocationRepository locationRepository;
    @Override
    public CreateLocationResponse createLocation(CreateLocationRequest request) {
        LocationEntity savedLocation = saveNewLocation(request);
        return CreateLocationResponse.builder()
                .locationId(savedLocation.getId())
                .build();
    }

    private LocationEntity saveNewLocation(CreateLocationRequest request){
        LocationEntity newLocation = LocationEntity.builder()
                .floor(request.getFloor())
                .parkingSlot(request.getParkingSlot())
                .build();
        return locationRepository.save(newLocation);
    }
}
