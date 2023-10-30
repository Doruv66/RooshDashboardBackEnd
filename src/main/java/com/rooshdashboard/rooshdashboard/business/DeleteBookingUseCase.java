package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.booking.DeleteBookingResponse;

public interface DeleteBookingUseCase {
    DeleteBookingResponse deleteBooking(long id);
}
