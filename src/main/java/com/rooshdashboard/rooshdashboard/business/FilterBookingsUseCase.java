package com.rooshdashboard.rooshdashboard.business;
import com.rooshdashboard.rooshdashboard.domain.booking.FilterBookingRequest;
import com.rooshdashboard.rooshdashboard.domain.booking.FilterBookingResponse;
public interface FilterBookingsUseCase {
    FilterBookingResponse filterBookings(FilterBookingRequest request);
}
