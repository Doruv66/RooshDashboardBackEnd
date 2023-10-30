package com.rooshdashboard.rooshdashboard.persistance;

import com.rooshdashboard.rooshdashboard.persistance.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
