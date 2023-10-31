package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.service.CreateServiceRequest;
import com.rooshdashboard.rooshdashboard.domain.service.CreateServiceResponse;

public interface CreateServiceUseCase {
    CreateServiceResponse createService(CreateServiceRequest request);
}
