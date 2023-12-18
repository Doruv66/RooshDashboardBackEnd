package com.rooshdashboard.rooshdashboard.domain.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyStatistics {
    private int numOfBookings;
    private double revenue;
}
