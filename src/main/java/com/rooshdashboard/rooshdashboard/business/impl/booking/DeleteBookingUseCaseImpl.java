package com.rooshdashboard.rooshdashboard.business.impl.booking;

import com.rooshdashboard.rooshdashboard.business.DeleteBookingUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidBookingException;
import com.rooshdashboard.rooshdashboard.domain.booking.DeleteBookingResponse;
import com.rooshdashboard.rooshdashboard.persistance.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteBookingUseCaseImpl implements DeleteBookingUseCase {
    private final BookingRepository bookingRepository;
    @Override
    public DeleteBookingResponse deleteBooking(long id) {
        if(!this.bookingRepository.existsById(id)){
            this.bookingRepository.deleteById(id);
            return DeleteBookingResponse.builder().id(id).build();
        }
        throw new InvalidBookingException("BOOKING_COULD_NOT_BE_FOUND");
    }
}
