package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.booking.Booking;
import com.rooshdashboard.rooshdashboard.domain.booking.GetAllBookingResponse;

import java.util.List;

public interface GetAllBookingsUseCase {
    GetAllBookingResponse getAllBookings();
}
