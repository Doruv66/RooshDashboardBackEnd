package com.rooshdashboard.rooshdashboard.domain.ParkingGarage;

import com.rooshdashboard.rooshdashboard.domain.Account.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingGarage {
    private Long id;
    private String name;
    private Account account;
    private String airport;
    private String location;
    private Long travelTime;
    private Long travelDistance;
    private String phoneNumber;

}
