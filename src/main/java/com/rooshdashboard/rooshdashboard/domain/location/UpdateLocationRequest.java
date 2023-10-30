package com.rooshdashboard.rooshdashboard.domain.location;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLocationRequest {
    @NotNull
    private Long id;
    @NotNull
    private int parkingSlot;
    @NotNull
    private int floor;
}
