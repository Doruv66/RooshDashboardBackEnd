package com.rooshdashboard.rooshdashboard.business.IAccount;

import com.rooshdashboard.rooshdashboard.domain.User.CreateUserRequest;
import com.rooshdashboard.rooshdashboard.domain.User.CreateUserResponse;

public interface CreateAccountUseCase {
    CreateUserResponse CreateAccounts(CreateUserRequest request);
}
