package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.booking.UpdateBookingRequest;
import com.rooshdashboard.rooshdashboard.domain.booking.UpdateBookingResponse;

public interface UpdateBookingUseCase {
    UpdateBookingResponse updateBooking(UpdateBookingRequest request);
}
