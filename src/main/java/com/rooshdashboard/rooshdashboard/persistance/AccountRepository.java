package com.rooshdashboard.rooshdashboard.persistance;

import com.rooshdashboard.rooshdashboard.persistance.entity.AccountEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository {
    Optional<AccountEntity> findById(long accountId);

    AccountEntity save(AccountEntity account);

    List<AccountEntity> findAll();

    void deleteById(long accountId);

    boolean accountExists(String accountId);

}
