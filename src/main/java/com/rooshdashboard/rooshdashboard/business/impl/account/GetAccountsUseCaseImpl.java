package com.rooshdashboard.rooshdashboard.business.impl.account;

import com.rooshdashboard.rooshdashboard.business.IAccount.GetAccountsUseCase;
import com.rooshdashboard.rooshdashboard.domain.Account.Account;
import com.rooshdashboard.rooshdashboard.domain.Account.GetAccountResponse;
import com.rooshdashboard.rooshdashboard.persistance.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAccountsUseCaseImpl implements GetAccountsUseCase {
    private final AccountRepository accountRepository;

    @Override
    public GetAccountResponse getAccounts() {
        List<Account> accounts = accountRepository.findAll()
                .stream()
                .map(AccountConverter::convert)
                .toList();
        return GetAccountResponse.builder()
                .accounts(accounts)
                .build();

    }
}
