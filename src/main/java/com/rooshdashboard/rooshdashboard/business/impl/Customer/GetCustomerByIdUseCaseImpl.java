package com.rooshdashboard.rooshdashboard.business.impl.Customer;

import com.rooshdashboard.rooshdashboard.business.GetCustomerByIdUseCase;
import com.rooshdashboard.rooshdashboard.domain.Customer.Customer;
import com.rooshdashboard.rooshdashboard.persistance.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetCustomerByIdUseCaseImpl implements GetCustomerByIdUseCase {
    private final CustomerRepository customerRepository;

    @Override
    public Customer getCustomerById(Long customerId){
        return CustomerConverter.convert(customerRepository.findById(customerId));
    }
}
