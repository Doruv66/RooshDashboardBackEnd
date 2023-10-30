package com.rooshdashboard.rooshdashboard.business.impl.service;

import com.rooshdashboard.rooshdashboard.business.UpdateServiceUseCase;
import com.rooshdashboard.rooshdashboard.domain.service.UpdateServiceRequest;
import com.rooshdashboard.rooshdashboard.domain.service.UpdateServiceResponse;
import com.rooshdashboard.rooshdashboard.persistance.ServiceRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.ServiceEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateServiceUseCaseImpl implements UpdateServiceUseCase {

    private final ServiceRepository serviceRepository;

    @Override
    public UpdateServiceResponse updateService(UpdateServiceRequest request) {
        if(serviceRepository.getServicesById(request.getId()) == null) {
            //throw not found exception
        }
        ServiceEntity serviceEntity = serviceRepository.getServicesById(request.getId());
        long updatedCarId = updateFields(request, serviceEntity);
        return UpdateServiceResponse.builder()
                .id(updatedCarId)
                .build();
    }

    private Long updateFields(UpdateServiceRequest request, ServiceEntity service) {
        service.setServiceType(request.getType());
        service.setPrice(request.getPrice());
        return serviceRepository.saveService(service);
    }
}
