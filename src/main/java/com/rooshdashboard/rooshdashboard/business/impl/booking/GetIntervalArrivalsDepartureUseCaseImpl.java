package com.rooshdashboard.rooshdashboard.business.impl.booking;

import com.rooshdashboard.rooshdashboard.business.GetIntervalArrivalsDeparturesUseCase;
import com.rooshdashboard.rooshdashboard.domain.booking.Booking;
import com.rooshdashboard.rooshdashboard.domain.booking.GetArrivalsDepartures;
import com.rooshdashboard.rooshdashboard.persistance.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class GetIntervalArrivalsDepartureUseCaseImpl implements GetIntervalArrivalsDeparturesUseCase {
    private final BookingRepository bookingRepository;
    @Override
    public GetArrivalsDepartures getIntervalArrivalDepartures(LocalDate startTime, LocalDate endTime) {
        List<Booking> arrivals = bookingRepository.findAllByStartTimeInterval(startTime, endTime).stream()
                .map(BookingConverters::convertToBooking)
                .toList();
        List<Booking> departures = bookingRepository.findAllByEndTimeInterval(startTime, endTime).stream()
                .map(BookingConverters::convertToBooking)
                .toList();

        return GetArrivalsDepartures.builder()
                .arrivals(arrivals)
                .departures(departures)
                .build();
    }
}
