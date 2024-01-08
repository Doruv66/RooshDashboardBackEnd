package com.rooshdashboard.rooshdashboard.business.impl.account;

import com.rooshdashboard.rooshdashboard.domain.User.Role;
import com.rooshdashboard.rooshdashboard.persistance.entity.UserRoleEntity;

public class RoleConverter {
    public static Role convert(UserRoleEntity role) {
        return Role.builder()
                .id(role.getId())
                .roleName(role.getRole().name())
                .build();
    }
}
