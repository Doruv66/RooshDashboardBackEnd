package com.rooshdashboard.rooshdashboard.business.impl.Customer;

import com.rooshdashboard.rooshdashboard.business.CreateCustomerUseCase;
import com.rooshdashboard.rooshdashboard.domain.Customer.CreateCustomerRequest;
import com.rooshdashboard.rooshdashboard.domain.Customer.CreateCustomerResponse;
import com.rooshdashboard.rooshdashboard.persistance.CustomerRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {
    private final CustomerRepository customerRepository;

    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerRequest request){
        Long newCustomerCustomerId = saveNewCustomer(request);

        return CreateCustomerResponse.builder()
                .customerId(newCustomerCustomerId)
                .build();
    }

    private Long saveNewCustomer(CreateCustomerRequest request){
        CustomerEntity newCustomer = CustomerEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();
        return customerRepository.save(newCustomer);
    }
}
