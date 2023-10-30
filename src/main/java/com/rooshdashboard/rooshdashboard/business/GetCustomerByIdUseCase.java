package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.Customer.Customer;

public interface GetCustomerByIdUseCase {
    Customer getCustomerById(Long customerId);
}
