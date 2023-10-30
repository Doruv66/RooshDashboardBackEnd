package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.location.CreateLocationRequest;
import com.rooshdashboard.rooshdashboard.domain.location.CreateLocationResponse;

public interface CreateLocationUseCase {
    CreateLocationResponse createLocation(CreateLocationRequest request);
}
