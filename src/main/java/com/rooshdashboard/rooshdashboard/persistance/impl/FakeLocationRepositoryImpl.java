package com.rooshdashboard.rooshdashboard.persistance.impl;

import com.rooshdashboard.rooshdashboard.persistance.LocationRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.LocationEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class FakeLocationRepositoryImpl implements LocationRepository {
    @Override
    public boolean existsById(long locationId) {
        return false;
    }

    @Override
    public Optional<LocationEntity> findById(long locationId) {
        return Optional.empty();
    }

    @Override
    public LocationEntity save(LocationEntity location) {
        return null;
    }

    @Override
    public List<LocationEntity> findAll() {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public void deleteById(long locationId) {

    }
}
