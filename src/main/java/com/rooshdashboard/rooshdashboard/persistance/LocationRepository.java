package com.rooshdashboard.rooshdashboard.persistance;

import com.rooshdashboard.rooshdashboard.persistance.entity.LocationEntity;
import java.util.Optional;
import java.util.List;
public interface LocationRepository {
    boolean existsById(long locationId);

    Optional<LocationEntity> findById(long locationId);

    LocationEntity save(LocationEntity location);

    List<LocationEntity> findAll();

    int count();
    void deleteById(long locationId);
}
