package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.service.DeleteServiceResponse;

public interface DeleteServiceUseCase {
    DeleteServiceResponse deleteService(long id);
}
