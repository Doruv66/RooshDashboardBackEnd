package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.location.Location;
import java.util.Optional;
public interface GetLocationByIdUseCase {
    Optional<Location> getLocation(long locationId);
}
