package com.rooshdashboard.rooshdashboard.persistance;

import com.rooshdashboard.rooshdashboard.persistance.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository {
    boolean existsById(long customerId);
    CustomerEntity findById(long customerId);
    List<CustomerEntity> findAll();
    Long save(CustomerEntity customer);
    void deleteById(long customerId);
}
