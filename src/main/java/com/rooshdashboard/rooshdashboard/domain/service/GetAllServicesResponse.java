package com.rooshdashboard.rooshdashboard.domain.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GetAllServicesResponse {
    private List<Service> services;
}
