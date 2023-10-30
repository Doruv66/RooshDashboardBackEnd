package com.rooshdashboard.rooshdashboard.business.impl.booking;

import com.rooshdashboard.rooshdashboard.domain.booking.Booking;
import com.rooshdashboard.rooshdashboard.domain.booking.GetAllBookingResponse;
import com.rooshdashboard.rooshdashboard.persistance.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class GetAllBookingsUseCase implements com.rooshdashboard.rooshdashboard.business.GetAllBookingsUseCase {
    private final BookingRepository bookingRepository;
    @Override
    public GetAllBookingResponse getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll()
                .stream()
                .map(BookingConverters::convertToBooking)
                .toList();

        return GetAllBookingResponse.builder().bookings(bookings).build();
    }
}
