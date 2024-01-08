package com.rooshdashboard.rooshdashboard.domain.User;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetUserResponse {
    private List<User> accounts;
}
