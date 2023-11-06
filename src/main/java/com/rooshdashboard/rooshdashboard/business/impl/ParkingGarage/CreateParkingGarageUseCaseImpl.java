package com.rooshdashboard.rooshdashboard.business.impl.ParkingGarage;

import com.rooshdashboard.rooshdashboard.business.IParkingGarage.CreateParkingGarageUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidParkingGarageExeption;
import com.rooshdashboard.rooshdashboard.domain.ParkingGaragee.CreateParkingGarageRequest;
import com.rooshdashboard.rooshdashboard.domain.ParkingGaragee.CreateParkingGarageResponse;
import com.rooshdashboard.rooshdashboard.persistance.ParkingGarageRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateParkingGarageUseCaseImpl implements CreateParkingGarageUseCase {
    private final ParkingGarageRepository parkingGarageRepository;

    @Override
    public CreateParkingGarageResponse CreateParkingGarage(CreateParkingGarageRequest request)
    {
        if(request.getLocation().isEmpty() || request.getBookingId() != null) {
            ParkingGarageEntity parkingGarage = saveNewParkingGarage(request);
            return CreateParkingGarageResponse.builder()
                    .id(parkingGarage.getId())
                    .build();
        }
        throw new  InvalidParkingGarageExeption("COULD_NOT_CREATE_PARKING_GARAGE");
    }

    private ParkingGarageEntity saveNewParkingGarage(CreateParkingGarageRequest request) {
        ParkingGarageEntity newParkingGarage = ParkingGarageEntity.builder()
                .location(request.getLocation())
                .bookingId(request.getBookingId())
                .parkingGarageUtility(request.getParkingGarageUtility())
                .build();
        return parkingGarageRepository.save(newParkingGarage);
    }
}
