package com.rooshdashboard.rooshdashboard.persistance;

import com.rooshdashboard.rooshdashboard.persistance.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    @Query("SELECT a from AccountEntity a WHERE lower(a.email) LIKE lower(concat('%', :email, '%'))")
    String checkIfEmailIsUsed(@Param("email") String email);
}
