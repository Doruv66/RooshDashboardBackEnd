package com.rooshdashboard.rooshdashboard.business.impl.booking;

import com.rooshdashboard.rooshdashboard.business.GetBookingByIdUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidBookingException;
import com.rooshdashboard.rooshdashboard.domain.booking.Booking;
import com.rooshdashboard.rooshdashboard.domain.booking.GetBookingByIdResponse;
import com.rooshdashboard.rooshdashboard.persistance.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetBookingByIdUseCaseImpl implements GetBookingByIdUseCase {
    private final BookingRepository bookingRepository;
    @Override
    public GetBookingByIdResponse getBookingById(long id) {
        if(bookingRepository.existsById(id)){
            Optional<Booking> optionalBooking = bookingRepository.findById(id).map(BookingConverters::convertToBooking);
            Booking booking = optionalBooking.get();
            return GetBookingByIdResponse.builder().booking(booking).build();
        }
        throw new InvalidBookingException("BOOKING_NOT_FOUND");
    }
}
