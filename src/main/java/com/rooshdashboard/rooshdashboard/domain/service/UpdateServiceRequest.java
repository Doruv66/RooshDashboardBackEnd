package com.rooshdashboard.rooshdashboard.domain.service;

import com.rooshdashboard.rooshdashboard.persistance.entity.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateServiceRequest {
    private long id;
    private double price;
    private ServiceType type;
}
