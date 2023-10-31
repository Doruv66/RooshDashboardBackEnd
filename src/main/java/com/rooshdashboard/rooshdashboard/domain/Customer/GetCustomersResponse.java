package com.rooshdashboard.rooshdashboard.domain.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomersResponse {
    private List<Customer> customers;
}
