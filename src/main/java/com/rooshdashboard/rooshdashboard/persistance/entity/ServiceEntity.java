package com.rooshdashboard.rooshdashboard.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceEntity {
    private Long id;
    private double price;
    private ServiceType serviceType;
}
