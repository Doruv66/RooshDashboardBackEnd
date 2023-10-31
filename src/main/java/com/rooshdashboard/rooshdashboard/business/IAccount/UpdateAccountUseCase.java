package com.rooshdashboard.rooshdashboard.business.IAccount;

import com.rooshdashboard.rooshdashboard.domain.Account.UpdateAccountRequest;
import com.rooshdashboard.rooshdashboard.domain.Account.UpdateAccountResponse;

public interface UpdateAccountUseCase {
    UpdateAccountResponse updateAccount(UpdateAccountRequest request);
}
