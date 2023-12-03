package com.rooshdashboard.rooshdashboard.business.impl.booking;

import com.rooshdashboard.rooshdashboard.business.GetArrivalsDeparturesUseCase;
import com.rooshdashboard.rooshdashboard.domain.booking.Booking;
import com.rooshdashboard.rooshdashboard.domain.booking.GetArrivalsDepartures;
import com.rooshdashboard.rooshdashboard.persistance.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class GetArrivalsDeparturesUseCaseImpl implements GetArrivalsDeparturesUseCase {

    private final BookingRepository bookingRepository;


    @Transactional
    @Override
    public GetArrivalsDepartures getArrivalsDepartures(LocalDate date, long garageId) {
        List<Booking> departures = bookingRepository.findAllByEndDate(date, garageId).stream()
                .map(BookingConverters::convertToBooking)
                .toList();
        List<Booking> arrivals = bookingRepository.findAllByStartDate(date, garageId).stream()
                .map(BookingConverters::convertToBooking)
                .toList();

        return GetArrivalsDepartures.builder()
                .arrivals(arrivals)
                .departures(departures)
                .build();
    }
}
