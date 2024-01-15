package com.rooshdashboard.rooshdashboard.domain.User;

import com.rooshdashboard.rooshdashboard.persistance.entity.UserRoleEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UpdateUserRequest {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotNull
    private Set<UserRoleEntity> roles;
}
