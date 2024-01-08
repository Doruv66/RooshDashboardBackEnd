package com.rooshdashboard.rooshdashboard.business.impl.account;

import com.rooshdashboard.rooshdashboard.business.IAccount.GetAccountsUseCase;
import com.rooshdashboard.rooshdashboard.domain.User.User;
import com.rooshdashboard.rooshdashboard.domain.User.GetUserResponse;
import com.rooshdashboard.rooshdashboard.persistance.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetUserUseCaseImpl implements GetAccountsUseCase {
    private final UserRepository userRepository;

    @Override
    public GetUserResponse getAccounts() {
        List<User> users = userRepository.findAll()
                .stream()
                .map(UserConverter::convert)
                .toList();
        return GetUserResponse.builder()
                .accounts(users)
                .build();

    }
}
