package com.rooshdashboard.rooshdashboard.business.impl.account;

import com.rooshdashboard.rooshdashboard.domain.Account.Account;
import com.rooshdashboard.rooshdashboard.persistance.entity.AccountEntity;

public class AccountConverter {

    private AccountConverter() {

    }
    public static Account convert(AccountEntity account) {
        return Account.builder()
                .id(account.getId())
                .name(account.getName())
                .email(account.getEmail())
                .password(account.getPassword())
                .role(RoleConverter.convert(account.getRole()))
                .build();
    }
}
