package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.booking.GetArrivalsDepartures;

import java.time.LocalDate;

public interface GetArrivalsDeparturesUseCase {
    GetArrivalsDepartures getArrivalsDepartures(LocalDate date, long garageId);
}
