package com.rooshdashboard.rooshdashboard.business.impl.ParkingGarage;

import com.rooshdashboard.rooshdashboard.business.IParkingGarage.CreateParkingGarageUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidParkingGarageExeption;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.CreateParkingGarageRequest;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.CreateParkingGarageResponse;
import com.rooshdashboard.rooshdashboard.persistance.AccountRepository;
import com.rooshdashboard.rooshdashboard.persistance.ParkingGarageRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.AccountEntity;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateParkingGarageUseCaseImpl implements CreateParkingGarageUseCase {
    private final ParkingGarageRepository parkingGarageRepository;
    private final AccountRepository accountRepository;

    @Override
    public CreateParkingGarageResponse CreateParkingGarage(CreateParkingGarageRequest request)
    {
        if(request.getLocation().isEmpty()) {
            ParkingGarageEntity parkingGarage = saveNewParkingGarage(request);
            return CreateParkingGarageResponse.builder()
                    .id(parkingGarage.getId())
                    .build();
        }
        throw new  InvalidParkingGarageExeption("COULD_NOT_CREATE_PARKING_GARAGE");
    }

    private ParkingGarageEntity saveNewParkingGarage(CreateParkingGarageRequest request) {
        AccountEntity foundAccount = accountRepository.getReferenceById(request.getAccountId());
        ParkingGarageEntity newParkingGarage = ParkingGarageEntity.builder()
                .location(request.getLocation())
                .name(request.getName())
                .account(foundAccount)
                .travelDistance(request.getTravelDistance())
                .travelTime(request.getTravelTime())
                .location(request.getLocation())
                .phoneNumber(request.getPhoneNumber())
                .airport(request.getAirport())
                .build();
        return parkingGarageRepository.save(newParkingGarage);
    }
}
