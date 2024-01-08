package com.rooshdashboard.rooshdashboard.persistance;

import com.rooshdashboard.rooshdashboard.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    @Query("SELECT a from UserEntity a WHERE lower(a.username) LIKE lower(concat('%', :username, '%'))")
    String checkIfNameIsUsed(@Param("username") String username);
}
