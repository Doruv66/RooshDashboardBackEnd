package com.rooshdashboard.rooshdashboard.business.IAccount;

import com.rooshdashboard.rooshdashboard.domain.User.UpdateUserRequest;
import com.rooshdashboard.rooshdashboard.domain.User.UpdateUserResponse;

public interface UpdateAccountUseCase {
    UpdateUserResponse updateAccount(UpdateUserRequest request);
}
