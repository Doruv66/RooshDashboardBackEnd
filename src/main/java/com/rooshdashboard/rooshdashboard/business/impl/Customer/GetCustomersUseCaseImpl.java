package com.rooshdashboard.rooshdashboard.business.impl.Customer;

import com.rooshdashboard.rooshdashboard.business.GetCustomersUseCase;
import com.rooshdashboard.rooshdashboard.domain.Customer.Customer;
import com.rooshdashboard.rooshdashboard.domain.Customer.GetCustomersResponse;
import com.rooshdashboard.rooshdashboard.persistance.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetCustomersUseCaseImpl implements GetCustomersUseCase {
    private CustomerRepository customerRepository;

    @Override
    public GetCustomersResponse getCustomers(){
        List<Customer> customers = customerRepository.findAll()
                .stream()
                .map(CustomerConverter::convert)
                .toList();

        return GetCustomersResponse.builder()
                .customers(customers)
                .build();
    }
}
