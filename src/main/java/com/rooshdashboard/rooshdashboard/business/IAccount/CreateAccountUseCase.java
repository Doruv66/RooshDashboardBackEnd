package com.rooshdashboard.rooshdashboard.business.IAccount;

import com.rooshdashboard.rooshdashboard.domain.Account.CreateAccountRequest;
import com.rooshdashboard.rooshdashboard.domain.Account.CreateAccountResponse;

public interface CreateAccountUseCase {
    CreateAccountResponse CreateAccounts(CreateAccountRequest request);
}
