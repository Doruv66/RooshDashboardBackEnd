package com.rooshdashboard.rooshdashboard.business.IAccount;

import com.rooshdashboard.rooshdashboard.domain.Account.DeleteAccountResponse;

public interface DeleteAccountUseCase {
    DeleteAccountResponse deleteAccount(long accountId);
}
