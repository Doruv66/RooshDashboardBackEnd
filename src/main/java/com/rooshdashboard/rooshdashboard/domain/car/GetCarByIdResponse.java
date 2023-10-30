package com.rooshdashboard.rooshdashboard.domain.car;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCarByIdResponse {
    private Car car;
}
