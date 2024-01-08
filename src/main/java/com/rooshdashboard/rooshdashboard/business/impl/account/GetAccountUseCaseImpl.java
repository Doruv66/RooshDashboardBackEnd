package com.rooshdashboard.rooshdashboard.business.impl.account;

import com.rooshdashboard.rooshdashboard.business.IAccount.GetAccountUseCase;
import com.rooshdashboard.rooshdashboard.domain.User.User;
import com.rooshdashboard.rooshdashboard.persistance.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetAccountUseCaseImpl implements GetAccountUseCase {
    private final UserRepository userRepository;

    @Override
    public Optional<User> getAccount(long accountId) {
        return userRepository.findById(accountId).map(UserConverter::convert);
    }

}
