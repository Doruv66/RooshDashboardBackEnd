package com.rooshdashboard.rooshdashboard.configuration.security.token;

import java.util.Set;

public interface AccessToken {
    String getSubject();

    Long getAccountId();

    Set<String> getRoles();

    boolean hasRole(String roleName);
}
