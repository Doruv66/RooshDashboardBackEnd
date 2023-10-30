package com.rooshdashboard.rooshdashboard.business.impl.location;

import com.rooshdashboard.rooshdashboard.business.DeleteLocationUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidLocationException;
import com.rooshdashboard.rooshdashboard.persistance.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteLocationUseCaseImpl implements DeleteLocationUseCase {
    private final LocationRepository locationRepository;


    @Override
    public void deleteLocation(long locationId) {
        if(this.locationRepository.existsById(locationId)){
            this.locationRepository.deleteById(locationId);
        }
        else {
            throw new InvalidLocationException("LOCATION_ID_INVALID");
        }
    }
}
