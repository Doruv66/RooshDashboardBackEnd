package com.rooshdashboard.rooshdashboard.business.impl.service;

import com.rooshdashboard.rooshdashboard.business.DeleteServiceUseCase;
import com.rooshdashboard.rooshdashboard.domain.service.DeleteServiceResponse;
import com.rooshdashboard.rooshdashboard.persistance.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteServiceUseCaseImpl implements DeleteServiceUseCase {
    private final ServiceRepository serviceRepository;

    @Override
    public DeleteServiceResponse deleteService(long id) {
        if(serviceRepository.getServicesById(id) == null) {
            //throw exception
        }
        Long deletedServiceId = serviceRepository.deleteById(id);
        return DeleteServiceResponse.builder()
                .id(deletedServiceId)
                .build();
    }
}
