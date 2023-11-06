package com.rooshdashboard.rooshdashboard.business.impl.booking;

import com.rooshdashboard.rooshdashboard.business.UpdateBookingUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidBookingException;
import com.rooshdashboard.rooshdashboard.domain.booking.Booking;
import com.rooshdashboard.rooshdashboard.domain.booking.UpdateBookingRequest;
import com.rooshdashboard.rooshdashboard.domain.booking.UpdateBookingResponse;
import com.rooshdashboard.rooshdashboard.persistance.BookingRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.BookingEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateBookingUseCaseImpl implements UpdateBookingUseCase {
    private final BookingRepository bookingRepository;
    @Override
    public UpdateBookingResponse updateBooking(UpdateBookingRequest request) {
        if(!bookingRepository.existsById(request.getId())){
            throw new InvalidBookingException("BOOKING_NOT_FOUND");
        }

        Optional<Booking> optionalBooking = bookingRepository.findById(request.getId()).map(BookingConverters::convertToBooking);
        return UpdateBookingResponse.builder().id(optionalBooking.get().getId()).build();
    }

    private Long updateFields(UpdateBookingRequest request, BookingEntity bookingEntity){
        bookingEntity.setId(request.getId());
        bookingEntity.setCustomerId(request.getCustomerId());
        bookingEntity.setCarId(request.getCarId());
        bookingEntity.setGarageId(request.getGarageId());
        bookingEntity.setEndDate(request.getEndDate());
        bookingEntity.setEndDate(request.getEndDate());
        bookingEntity.setFlightNumberArrival(request.getFlightNumberArrival());
        bookingEntity.setFlightNumberDeparture(request.getFlightNumberDeparture());
        bookingRepository.save(bookingEntity);
        return bookingEntity.getId();
    }
}
