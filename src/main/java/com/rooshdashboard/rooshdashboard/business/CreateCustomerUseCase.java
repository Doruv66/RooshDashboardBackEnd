package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.Customer.CreateCustomerRequest;
import com.rooshdashboard.rooshdashboard.domain.Customer.CreateCustomerResponse;

public interface CreateCustomerUseCase {
    CreateCustomerResponse createCustomer(CreateCustomerRequest request);
}
