package com.rooshdashboard.rooshdashboard.business.impl.account;

import com.rooshdashboard.rooshdashboard.business.IAccount.GetAccountUseCase;
import com.rooshdashboard.rooshdashboard.domain.Account.Account;
import com.rooshdashboard.rooshdashboard.persistance.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetAccountUseCaseImpl implements GetAccountUseCase {
    private final AccountRepository accountRepository;

    @Override
    public Optional<Account> getAccount(long accountId) {
        return accountRepository.findById(accountId).map(AccountConverter::convert);
    }

}
