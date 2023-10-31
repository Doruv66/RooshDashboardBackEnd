package com.rooshdashboard.rooshdashboard.business.impl.account;

import com.rooshdashboard.rooshdashboard.business.IAccount.CreateAccountUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.AccountAlreadyExistsException;
import com.rooshdashboard.rooshdashboard.domain.Account.CreateAccountRequest;
import com.rooshdashboard.rooshdashboard.domain.Account.CreateAccountResponse;
import com.rooshdashboard.rooshdashboard.persistance.AccountRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.AccountEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class CreateAccountUseCaseImpl implements CreateAccountUseCase {
    private final AccountRepository accountRepository;

    @Override
    public CreateAccountResponse CreateAccounts(CreateAccountRequest request) {

        if (Objects.equals(accountRepository.checkIfEmailIsUsed(request.getEmail()), request.getEmail())) {
            throw new AccountAlreadyExistsException();
        }

        AccountEntity savedAccount = saveNewAccount(request);

        return CreateAccountResponse.builder()
                .id(savedAccount.getId())
                .build();
    }

    private AccountEntity saveNewAccount(CreateAccountRequest request) {

        AccountEntity newAccount = AccountEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .RoleId(request.getRoleId())
                .build();
        return accountRepository.save(newAccount);

    }
}
