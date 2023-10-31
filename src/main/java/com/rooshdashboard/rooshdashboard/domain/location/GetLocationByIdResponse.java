package com.rooshdashboard.rooshdashboard.domain.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetLocationByIdResponse {
    private Optional<Location> location;
}
