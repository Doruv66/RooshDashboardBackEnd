package com.rooshdashboard.rooshdashboard.business.impl.account;

import com.rooshdashboard.rooshdashboard.business.IAccount.UpdateAccountUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidAccountException;
import com.rooshdashboard.rooshdashboard.domain.Account.UpdateAccountRequest;
import com.rooshdashboard.rooshdashboard.domain.Account.UpdateAccountResponse;
import com.rooshdashboard.rooshdashboard.persistance.AccountRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.AccountEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateAccountUseCaseImpl implements UpdateAccountUseCase {
    private final AccountRepository accountRepository;

    @Override
    public UpdateAccountResponse updateAccount(UpdateAccountRequest request) {
        Optional<AccountEntity> accountEntityOptional = accountRepository.findById(request.getId());
        if (accountEntityOptional.isEmpty()) {
            throw new InvalidAccountException("HOUSE_ID_INVALID");
        }

        AccountEntity account = accountEntityOptional.get();
        updateFields(request, account);

        return UpdateAccountResponse.builder()
                .message("Successfully update account with ID " + account.getId())
                .build();
    }

    private void updateFields(UpdateAccountRequest request, AccountEntity account) {
        account.setName(request.getName());
        account.setEmail(request.getEmail());
        account.setPassword(request.getPassword());
        account.setRole(request.getRole());

        accountRepository.save(account);
    }

}
