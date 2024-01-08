package com.rooshdashboard.rooshdashboard.domain.ParkingGarage;

import com.rooshdashboard.rooshdashboard.domain.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingGarage {
    private Long id;
    private String name;
    private User account;
    private String airport;
    private String location;
    private Long travelTime;
    private Long travelDistance;
    private String phoneNumber;
    private ParkingGarageUtility parkingGarageUtility;
    private List<String> imagePaths;
}
