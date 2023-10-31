package com.rooshdashboard.rooshdashboard.business.impl.Customer;

import com.rooshdashboard.rooshdashboard.business.UpdateCustomerUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidCustomerException;
import com.rooshdashboard.rooshdashboard.domain.Customer.UpdateCustomerRequest;
import com.rooshdashboard.rooshdashboard.domain.Customer.UpdateCustomerResponse;
import com.rooshdashboard.rooshdashboard.persistance.CustomerRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {
    private final CustomerRepository customerRepository;

    @Override
    public UpdateCustomerResponse updateCustomer(UpdateCustomerRequest request) {
        if(customerRepository.existsById(request.getId())){
            CustomerEntity customer = customerRepository.findById(request.getId()).get();
            updateFields(request, customer);
            return UpdateCustomerResponse.builder()
                    .customerId(request.getId())
                    .build();
        }
        else{
            throw new InvalidCustomerException("CUSTOMER_NOT_FOUND");
        }
    }

    private void updateFields(UpdateCustomerRequest request, CustomerEntity customer) {
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        customerRepository.deleteById(request.getId());
        customerRepository.save(customer);
    }
}
