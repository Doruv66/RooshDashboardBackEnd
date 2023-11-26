package com.rooshdashboard.rooshdashboard.domain.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetArrivalsDepartures {
    private List<Booking> arrivals;
    private List<Booking> departures;
}
