package com.rooshdashboard.rooshdashboard.domain.car;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllCarsResponse {
    private List<Car> cars;
}
