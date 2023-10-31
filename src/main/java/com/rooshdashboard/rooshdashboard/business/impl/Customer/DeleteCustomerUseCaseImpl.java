package com.rooshdashboard.rooshdashboard.business.impl.Customer;

import com.rooshdashboard.rooshdashboard.business.DeleteCustomerUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidCustomerException;
import com.rooshdashboard.rooshdashboard.persistance.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteCustomerUseCaseImpl implements DeleteCustomerUseCase {
    private final CustomerRepository customerRepository;
    @Override
    public void deleteCustomer(Long customerId){
        if(customerRepository.existsById(customerId)) {
            this.customerRepository.deleteById(customerId);
        }
        throw new InvalidCustomerException("CUSTOMER_NOT_FOUND");
    }
}
