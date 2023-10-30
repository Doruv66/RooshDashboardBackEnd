package com.rooshdashboard.rooshdashboard.business.impl.service;

import com.rooshdashboard.rooshdashboard.domain.service.Service;
import com.rooshdashboard.rooshdashboard.persistance.entity.ServiceEntity;

public class ServiceConverter {
    private ServiceConverter() {
    }

    public static Service convert(ServiceEntity service) {
        return Service.builder()
                .id(service.getId())
                .price(service.getPrice())
                .serviceType(service.getServiceType())
                .build();
    }
}
