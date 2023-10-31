package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.booking.GetBookingByIdResponse;

public interface GetBookingByIdUseCase {
    GetBookingByIdResponse getBookingById(long id);
}
