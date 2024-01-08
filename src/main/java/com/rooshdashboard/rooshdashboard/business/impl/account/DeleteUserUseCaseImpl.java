package com.rooshdashboard.rooshdashboard.business.impl.account;

import com.rooshdashboard.rooshdashboard.business.IAccount.DeleteAccountUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidAccountException;
import com.rooshdashboard.rooshdashboard.domain.User.DeleteUserResponse;
import com.rooshdashboard.rooshdashboard.persistance.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteAccountUseCase {
    private final UserRepository userRepository;

    @Override
    public DeleteUserResponse deleteAccount(long accountId) {
        if(!userRepository.existsById(accountId)){
            throw new InvalidAccountException("ACCOUNT_NOT_FOUND");
        }
        this.userRepository.deleteById(accountId);

        return DeleteUserResponse.builder()
                .message("Successfully deleted account with id " + accountId)
                .build();
    }
}
