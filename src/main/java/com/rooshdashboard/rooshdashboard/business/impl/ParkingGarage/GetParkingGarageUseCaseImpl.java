package com.rooshdashboard.rooshdashboard.business.impl.ParkingGarage;

import com.rooshdashboard.rooshdashboard.business.IParkingGarage.GetParkingGarageUseCase;
import com.rooshdashboard.rooshdashboard.configuration.security.token.AccessToken;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.GetParkingGarageResponse;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.ParkingGarage;
import com.rooshdashboard.rooshdashboard.persistance.ParkingGarageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetParkingGarageUseCaseImpl implements GetParkingGarageUseCase {
    private final ParkingGarageRepository parkingGarageRepository;
    private AccessToken requestAccessToken;


    @Override
    public GetParkingGarageResponse getParkingGarage() {
        Long userId = requestAccessToken.getAccountId();
        List<ParkingGarage> parkingGarages = parkingGarageRepository.findByAccount_Id(userId)
                .stream()
                .map(ParkingGarageConverter::convert)
                .toList();

        return GetParkingGarageResponse.builder()
                .parkingGarages(parkingGarages)
                .build();
    }
}
