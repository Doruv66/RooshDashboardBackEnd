package com.rooshdashboard.rooshdashboard.business.impl.account;

import com.rooshdashboard.rooshdashboard.business.IAccount.DeleteAccountUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidAccountException;
import com.rooshdashboard.rooshdashboard.domain.Account.DeleteAccountResponse;
import com.rooshdashboard.rooshdashboard.persistance.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAccountUseCaseImpl implements DeleteAccountUseCase {
    private final AccountRepository accountRepository;

    @Override
    public DeleteAccountResponse deleteAccount(long accountId) {
        if(!accountRepository.existsById(accountId)){
            throw new InvalidAccountException("ACCOUNT_NOT_FOUND");
        }
        this.accountRepository.deleteById(accountId);

        return DeleteAccountResponse.builder()
                .message("Successfully deleted account with id " + accountId)
                .build();
    }
}
