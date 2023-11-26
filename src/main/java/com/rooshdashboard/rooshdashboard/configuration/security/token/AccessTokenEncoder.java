package com.rooshdashboard.rooshdashboard.configuration.security.token;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
