package com.rooshdashboard.rooshdashboard.domain.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetBookingStatisticsRequest {
    private LocalDate startDate;
    private Long garageId;
}
