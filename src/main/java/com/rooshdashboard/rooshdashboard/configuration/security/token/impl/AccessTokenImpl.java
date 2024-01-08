package com.rooshdashboard.rooshdashboard.configuration.security.token.impl;

import com.rooshdashboard.rooshdashboard.configuration.security.token.AccessToken;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@EqualsAndHashCode
@Getter
public class AccessTokenImpl implements AccessToken {
    private final String subject;
    private final Set<String> roles;
    private final Long accountId;

    public AccessTokenImpl(String subject, Collection<String> roles, Long accountId) {
        this.subject = subject;
        this.roles = roles != null ? Set.copyOf(roles) : Collections.emptySet();
        this.accountId = accountId;
    }

    @Override
    public boolean hasRole(String roleName) {
        return this.roles.contains(roleName);
    }
}
