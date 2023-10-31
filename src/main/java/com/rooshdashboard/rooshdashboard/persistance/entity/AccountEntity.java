package com.rooshdashboard.rooshdashboard.persistance.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountEntity {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Long RoleId;
}
