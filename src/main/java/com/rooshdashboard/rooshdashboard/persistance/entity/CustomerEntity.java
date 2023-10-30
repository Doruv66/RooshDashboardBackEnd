package com.rooshdashboard.rooshdashboard.persistance.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerEntity {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
}
