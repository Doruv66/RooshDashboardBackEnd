package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.booking.CreateBookingRequest;
import com.rooshdashboard.rooshdashboard.domain.booking.CreateBookingResponse;

public interface CreateBookingUseCase {
    CreateBookingResponse createBooking(CreateBookingRequest request);
}
