package com.rooshdashboard.rooshdashboard.business.impl.ParkingGarage;

import com.rooshdashboard.rooshdashboard.business.IParkingGarage.GetParkingGaragesByUserIdUseCase;
import com.rooshdashboard.rooshdashboard.configuration.security.token.AccessToken;
import com.rooshdashboard.rooshdashboard.configuration.security.token.exception.InvalidAccessTokenException;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.GetParkingGaragesByUserIdResponse;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.ParkingGarage;
import com.rooshdashboard.rooshdashboard.persistance.ParkingGarageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetParkingGaragesByUserIdUseCaseImpl implements GetParkingGaragesByUserIdUseCase {

    private final ParkingGarageRepository parkingGarageRepository;
    private AccessToken requestAccessToken;

    @Override
    public GetParkingGaragesByUserIdResponse getParkingGaragesByUserId (Long userId) {
        List<ParkingGarage> garages;
        if (!userId.equals(requestAccessToken.getAccountId())) {
            garages = parkingGarageRepository.findByAccount_Id(userId)
                    .stream()
                    .map(ParkingGarageConverter::convert)
                    .collect(Collectors.toList());
        }
        else {
            throw new InvalidAccessTokenException("INVALID_ACCOUNT_ACCESS");
        }
        return GetParkingGaragesByUserIdResponse.builder().parkingGarages(garages).build();
    }
}
