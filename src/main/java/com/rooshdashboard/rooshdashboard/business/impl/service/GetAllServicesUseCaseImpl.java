package com.rooshdashboard.rooshdashboard.business.impl.service;

import com.rooshdashboard.rooshdashboard.business.GetAllServicesUseCase;
import com.rooshdashboard.rooshdashboard.domain.service.GetAllServicesResponse;
import com.rooshdashboard.rooshdashboard.domain.service.Service;

import com.rooshdashboard.rooshdashboard.persistance.ServiceRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class GetAllServicesUseCaseImpl implements GetAllServicesUseCase {
    private final ServiceRepository serviceRepository;

    @Override
    public GetAllServicesResponse getAllServices() {
        List<Service> services  = serviceRepository.findAll()
                .stream()
                .map(ServiceConverter::convert)
                .toList();

        return GetAllServicesResponse.builder()
                .services(services)
                .build();
    }
}
