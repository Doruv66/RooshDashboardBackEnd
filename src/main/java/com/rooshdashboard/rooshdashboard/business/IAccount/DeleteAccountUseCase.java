package com.rooshdashboard.rooshdashboard.business.IAccount;

import com.rooshdashboard.rooshdashboard.domain.User.DeleteUserResponse;

public interface DeleteAccountUseCase {
    DeleteUserResponse deleteAccount(long accountId);
}
