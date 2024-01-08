package com.rooshdashboard.rooshdashboard.business.impl.account;

import com.rooshdashboard.rooshdashboard.business.IAccount.CreateAccountUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.AccountAlreadyExistsException;
import com.rooshdashboard.rooshdashboard.domain.User.CreateUserRequest;
import com.rooshdashboard.rooshdashboard.domain.User.CreateUserResponse;
import com.rooshdashboard.rooshdashboard.persistance.UserRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateAccountUseCase {
    private final UserRepository userRepository;

    @Override
    public CreateUserResponse CreateAccounts(CreateUserRequest request) {

        if (Objects.equals(userRepository.checkIfNameIsUsed(request.getName()), request.getName())) {
            throw new AccountAlreadyExistsException();
        }

        UserEntity savedAccount = saveNewAccount(request);

        return CreateUserResponse.builder()
                .id(savedAccount.getId())
                .build();
    }

    private UserEntity saveNewAccount(CreateUserRequest request) {

        UserEntity newAccount = UserEntity.builder()
                .username(request.getName())
                .password(request.getPassword())
                .userRoles(request.getRoles())
                .build();
        return userRepository.save(newAccount);

    }
}
