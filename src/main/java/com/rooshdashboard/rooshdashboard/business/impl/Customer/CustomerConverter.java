package com.rooshdashboard.rooshdashboard.business.impl.Customer;

import com.rooshdashboard.rooshdashboard.domain.Customer.Customer;
import com.rooshdashboard.rooshdashboard.persistance.entity.CustomerEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomerConverter {
    public static Customer convert(CustomerEntity customer){
        return Customer.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }
}
