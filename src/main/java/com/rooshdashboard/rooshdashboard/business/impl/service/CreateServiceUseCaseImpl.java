package com.rooshdashboard.rooshdashboard.business.impl.service;

import com.rooshdashboard.rooshdashboard.business.CreateServiceUseCase;
import com.rooshdashboard.rooshdashboard.domain.service.CreateServiceRequest;
import com.rooshdashboard.rooshdashboard.domain.service.CreateServiceResponse;
import com.rooshdashboard.rooshdashboard.persistance.ServiceRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.ServiceEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateServiceUseCaseImpl implements CreateServiceUseCase {
    private final ServiceRepository serviceRepository;

    @Override
    public CreateServiceResponse createService(CreateServiceRequest request) {
        Long savedServiceId = saveNewService(request);
        return CreateServiceResponse.builder()
                .id(savedServiceId)
                .build();
    }

    private Long saveNewService(CreateServiceRequest request) {
        ServiceEntity newService = ServiceEntity.builder()
                .price(request.getPrice())
                .serviceType(request.getServiceType())
                .build();
        return serviceRepository.save(newService).getId();
    }
}
