package com.rooshdashboard.rooshdashboard.persistance;

import com.rooshdashboard.rooshdashboard.persistance.entity.CarEntity;
import com.rooshdashboard.rooshdashboard.persistance.entity.ServiceEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository {
    Long saveService(ServiceEntity service);
    Long deleteById(long serviceId);
    List<ServiceEntity> getAllServices();
    ServiceEntity getServicesById(long carId);
}
