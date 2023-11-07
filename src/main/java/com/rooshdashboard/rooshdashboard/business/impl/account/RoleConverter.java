package com.rooshdashboard.rooshdashboard.business.impl.account;

import com.rooshdashboard.rooshdashboard.domain.Account.Role;
import com.rooshdashboard.rooshdashboard.persistance.entity.RoleEntity;

public class RoleConverter {
    public static Role convert(RoleEntity role) {
        return Role.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .build();
    }
}
