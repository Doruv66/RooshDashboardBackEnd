package com.rooshdashboard.rooshdashboard.domain.User;

import com.rooshdashboard.rooshdashboard.persistance.entity.RoleEntity;
import com.rooshdashboard.rooshdashboard.persistance.entity.UserRoleEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotNull
    private Set<UserRoleEntity> roles;
}
