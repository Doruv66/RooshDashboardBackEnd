package com.rooshdashboard.rooshdashboard.business.impl.booking;

import com.rooshdashboard.rooshdashboard.business.GetBookingStatisticsUseCase;
import com.rooshdashboard.rooshdashboard.domain.booking.DailyStatistics;
import com.rooshdashboard.rooshdashboard.domain.booking.GetBookingStatisticsResponse;
import com.rooshdashboard.rooshdashboard.persistance.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetBookingStatisticsUseCaseImpl implements GetBookingStatisticsUseCase {
    private final BookingRepository bookingRepository;

    @Override
    public GetBookingStatisticsResponse getBookingStatistics(LocalDate date, long garageId) {
        LocalDateTime startDateTime = date.atStartOfDay();
        LocalDateTime endDateTime = LocalDate.now().plusDays(365).atStartOfDay();
        List<Object[]> statistics = bookingRepository.findBookingStatistics(garageId, startDateTime, endDateTime);


        Map<LocalDate, DailyStatistics> statisticsMap = statistics.stream()
                .collect(Collectors.toMap(
                        record -> convertToLocalDate((Date) record[0]), // Convert to LocalDate
                        record -> DailyStatistics.builder()
                                .numOfBookings(((Number) record[1]).intValue())
                                .revenue(((Number) record[2]).doubleValue())
                                .build(),
                        (existing, replacement) -> existing // In case of duplicate keys
                ));

        return GetBookingStatisticsResponse.builder()
                .statisticsMap(statisticsMap)
                .build();
    }

    private LocalDate convertToLocalDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }
}
