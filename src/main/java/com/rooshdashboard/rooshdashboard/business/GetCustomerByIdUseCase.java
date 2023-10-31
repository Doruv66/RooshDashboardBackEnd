package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.Customer.GetCustomerByIdResponse;

public interface GetCustomerByIdUseCase {
    GetCustomerByIdResponse getCustomerById(Long customerId);
}
