package com.rooshdashboard.rooshdashboard.business.impl.service;

import com.rooshdashboard.rooshdashboard.business.GetServiceByIdUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidServiceException;
import com.rooshdashboard.rooshdashboard.business.impl.car.CarConverter;
import com.rooshdashboard.rooshdashboard.domain.service.Service;
import com.rooshdashboard.rooshdashboard.persistance.ServiceRepository;
import lombok.AllArgsConstructor;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class GetServiceByIdUseCaseImpl implements GetServiceByIdUseCase {
    private final ServiceRepository serviceRepository;

    @Override
    public Service getServiceById(long id) {
        if(!serviceRepository.existsById(id)) {
            throw new InvalidServiceException("SERVICE_NOT_FOUND");
        }
        return ServiceConverter.convert(serviceRepository.findById(id).get());
    }
}
