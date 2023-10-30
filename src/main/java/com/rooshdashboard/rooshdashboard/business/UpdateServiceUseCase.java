package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.service.UpdateServiceRequest;
import com.rooshdashboard.rooshdashboard.domain.service.UpdateServiceResponse;

public interface UpdateServiceUseCase {
    UpdateServiceResponse updateService(UpdateServiceRequest request);
}
