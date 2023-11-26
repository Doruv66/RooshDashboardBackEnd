package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.booking.GetArrivalsDepartures;

import java.time.LocalDate;

public interface GetIntervalArrivalsDeparturesUseCase {
    GetArrivalsDepartures getIntervalArrivalDepartures(LocalDate startTime, LocalDate endTime);
}
