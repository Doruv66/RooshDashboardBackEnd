package com.rooshdashboard.rooshdashboard.domain.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetBookingStatisticsResponse {
    private Map<LocalDate, DailyStatistics> statisticsMap;
}
