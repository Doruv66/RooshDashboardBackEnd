package com.rooshdashboard.rooshdashboard.business.IAccount;

import com.rooshdashboard.rooshdashboard.domain.Account.Account;

import java.util.Optional;

public interface GetAccountUseCase {
    Optional<Account> getAccount(long accountId);
}
