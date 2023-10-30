package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.service.Service;

public interface GetServiceByIdUseCase {
    Service getServiceById(long id);

}
