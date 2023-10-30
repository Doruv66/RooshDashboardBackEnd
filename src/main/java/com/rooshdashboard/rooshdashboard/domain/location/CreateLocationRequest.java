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
public class CreateLocationRequest {
    @NotNull
    private Long locationId;
    @NotNull
    private int parkingSlot;
    @NotNull
    private int floor;
}
