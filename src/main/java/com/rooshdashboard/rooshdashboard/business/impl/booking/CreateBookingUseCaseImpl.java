package com.rooshdashboard.rooshdashboard.business.impl.booking;

import com.rooshdashboard.rooshdashboard.business.CreateBookingUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidBookingException;
import com.rooshdashboard.rooshdashboard.domain.booking.CreateBookingRequest;
import com.rooshdashboard.rooshdashboard.domain.booking.CreateBookingResponse;
import com.rooshdashboard.rooshdashboard.persistance.BookingRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.BookingEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateBookingUseCaseImpl implements CreateBookingUseCase {
    private final BookingRepository bookingRepository;

    @Override
    public CreateBookingResponse createBooking(CreateBookingRequest request) {
        if(request == null || bookingRepository.existsById(request.getId())){
            throw new InvalidBookingException("COULD_NOT_CREATE_BOOKING");
        }
        Long bookingId = saveBooking(request);

        return CreateBookingResponse.builder().id(bookingId).build();
    }

    private Long saveBooking(CreateBookingRequest request){
        BookingEntity newBookingEntity = BookingEntity.builder()
                .id(request.getId())
                .garage(request.getGarage())
                .service(request.getService())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .flightNumberArrival(request.getFlightNumberArrival())
                .flightNumberDeparture(request.getFlightNumberDeparture())
                .customer(request.getCustomer())
                .car(request.getCar())
                .build();
        bookingRepository.save(newBookingEntity);
        return newBookingEntity.getId();
    }
}
