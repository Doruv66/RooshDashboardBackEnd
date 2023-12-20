package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.booking.GetBookingStatisticsResponse;

import java.time.LocalDate;

public interface GetBookingStatisticsUseCase {
    GetBookingStatisticsResponse getBookingStatistics(LocalDate date, long garageId);
}
