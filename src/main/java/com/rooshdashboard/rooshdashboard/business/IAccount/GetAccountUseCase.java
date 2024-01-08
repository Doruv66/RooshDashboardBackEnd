package com.rooshdashboard.rooshdashboard.business.IAccount;

import com.rooshdashboard.rooshdashboard.domain.User.User;

import java.util.Optional;

public interface GetAccountUseCase {
    Optional<User> getAccount(long accountId);
}
