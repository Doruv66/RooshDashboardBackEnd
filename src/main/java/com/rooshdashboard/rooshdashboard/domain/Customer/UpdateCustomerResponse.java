package com.rooshdashboard.rooshdashboard.domain.Customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateCustomerResponse {
    private Long customerId;
}
