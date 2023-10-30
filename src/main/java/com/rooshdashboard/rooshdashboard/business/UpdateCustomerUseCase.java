package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.Customer.UpdateCustomerRequest;
import com.rooshdashboard.rooshdashboard.domain.Customer.UpdateCustomerResponse;

public interface UpdateCustomerUseCase {
    UpdateCustomerResponse updateCustomer(UpdateCustomerRequest request);
}
