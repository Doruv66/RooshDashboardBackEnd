package com.rooshdashboard.rooshdashboard.domain.service;

import com.rooshdashboard.rooshdashboard.persistance.entity.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateServiceRequest {
    private ServiceType serviceType;
}
