package com.rooshdashboard.rooshdashboard.business.impl.login;

import com.rooshdashboard.rooshdashboard.business.LoginUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidCredentialsException;
import com.rooshdashboard.rooshdashboard.configuration.security.token.AccessTokenEncoder;
import com.rooshdashboard.rooshdashboard.configuration.security.token.impl.AccessTokenImpl;
import com.rooshdashboard.rooshdashboard.domain.LoginRequest;
import com.rooshdashboard.rooshdashboard.domain.LoginResponse;
import com.rooshdashboard.rooshdashboard.persistance.UserRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null) {
            throw new InvalidCredentialsException();
        }

        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
         return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(UserEntity user) {
        List<String> roles = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole().toString())
                .toList();

        return accessTokenEncoder.encode(
                new AccessTokenImpl(user.getUsername(), roles));
    }

}
