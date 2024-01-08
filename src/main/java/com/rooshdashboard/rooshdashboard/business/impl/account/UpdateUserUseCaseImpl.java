package com.rooshdashboard.rooshdashboard.business.impl.account;

import com.rooshdashboard.rooshdashboard.business.IAccount.UpdateAccountUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidAccountException;
import com.rooshdashboard.rooshdashboard.domain.User.UpdateUserRequest;
import com.rooshdashboard.rooshdashboard.domain.User.UpdateUserResponse;
import com.rooshdashboard.rooshdashboard.persistance.UserRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateAccountUseCase {
    private final UserRepository userRepository;

    @Override
    public UpdateUserResponse updateAccount(UpdateUserRequest request) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(request.getId());
        if (userEntityOptional.isEmpty()) {
            throw new InvalidAccountException("ID_INVALID");
        }

        UserEntity account = userEntityOptional.get();
        updateFields(request, account);

        return UpdateUserResponse.builder()
                .message("Successfully update account with ID " + account.getId())
                .build();
    }

    private void updateFields(UpdateUserRequest request, UserEntity account) {
        account.setUsername(request.getName());
        account.setPassword(request.getPassword());
        account.setUserRoles(request.getRoles());

        userRepository.save(account);
    }

}
