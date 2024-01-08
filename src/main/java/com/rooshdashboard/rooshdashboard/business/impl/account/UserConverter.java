package com.rooshdashboard.rooshdashboard.business.impl.account;

import com.rooshdashboard.rooshdashboard.domain.User.User;
import com.rooshdashboard.rooshdashboard.domain.User.Role;
import com.rooshdashboard.rooshdashboard.persistance.entity.UserEntity;
import com.rooshdashboard.rooshdashboard.persistance.entity.UserRoleEntity;

import java.util.Set;

public class UserConverter {

    private UserConverter() {

    }
    public static User convert(UserEntity account) {
        Set<Role> convertedRoles = null;
        for (UserRoleEntity r: account.getUserRoles()) {
            convertedRoles.add(RoleConverter.convert(r));
        }
        return User.builder()
                .id(account.getId())
                .name(account.getUsername())
                .password(account.getPassword())
                .roles(convertedRoles)
                .build();
    }
}
