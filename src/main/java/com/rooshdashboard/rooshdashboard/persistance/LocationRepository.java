package com.rooshdashboard.rooshdashboard.persistance;

import com.rooshdashboard.rooshdashboard.persistance.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

}
