package com.rooshdashboard.rooshdashboard.business.location.impl;

import com.rooshdashboard.rooshdashboard.business.location.UpdateLocationUseCase;
import com.rooshdashboard.rooshdashboard.business.location.exception.InvalidLocationException;
import com.rooshdashboard.rooshdashboard.domain.location.UpdateLocationRequest;
import com.rooshdashboard.rooshdashboard.persistance.LocationRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.LocationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateLocationUseCaseImpl implements UpdateLocationUseCase {
    private final LocationRepository locationRepository;
    @Override
    public void updateLocation(UpdateLocationRequest request) {
        Optional<LocationEntity> locationOptional = locationRepository.findById(request.getId());
        if(locationOptional.isEmpty()){
            throw new InvalidLocationException("ITEM_ID_INVALID");
        }
        LocationEntity location = locationOptional.get();
        updateFields(request, location);
    }

    private void updateFields(UpdateLocationRequest request, LocationEntity location){
        location.setFloor(request.getFloor());
        location.setParkingSlot(request.getParkingSlot());

        locationRepository.save(location);
    }
}
