package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.Customer.DeleteCustomerResponse;

public interface DeleteCustomerUseCase {
    DeleteCustomerResponse deleteCustomer(Long customerId);
}
