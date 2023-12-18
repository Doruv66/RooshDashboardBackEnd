package com.rooshdashboard.rooshdashboard.business.impl.booking;

import com.rooshdashboard.rooshdashboard.business.GetIntervalArrivalsDeparturesUseCase;
import com.rooshdashboard.rooshdashboard.domain.booking.Booking;
import com.rooshdashboard.rooshdashboard.domain.booking.GetArrivalsDepartures;
import com.rooshdashboard.rooshdashboard.persistance.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class GetIntervalArrivalsDepartureUseCaseImpl implements GetIntervalArrivalsDeparturesUseCase {
    private final BookingRepository bookingRepository;

    @Transactional
    @Override
    public GetArrivalsDepartures getIntervalArrivalDepartures(LocalDate startTime, LocalDate endTime, long garageId) {
        LocalDateTime startDateTime = startTime.atStartOfDay().plusDays(1);
        LocalDateTime endDateTime = endTime.plusDays(1).atStartOfDay();
        List<Booking> arrivals = bookingRepository.findAllByStartTimeInterval(startDateTime, endDateTime, garageId).stream()
                .map(BookingConverters::convertToBooking)
                .toList();
        List<Booking> departures = bookingRepository.findAllByEndTimeInterval(startDateTime, endDateTime, garageId).stream()
                .map(BookingConverters::convertToBooking)
                .toList();

        return GetArrivalsDepartures.builder()
                .arrivals(arrivals)
                .departures(departures)
                .build();
    }
}
