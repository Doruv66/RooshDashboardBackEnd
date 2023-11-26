package com.rooshdashboard.rooshdashboard.business.impl.ParkingGarage;

import com.rooshdashboard.rooshdashboard.business.IParkingGarage.UpdateParkingGarageUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidParkingGarageExeption;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.UpdateParkingGarageRequest;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.UpdateParkingGarageResponse;
import com.rooshdashboard.rooshdashboard.persistance.ParkingGarageRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateParkingGarageUseCaseImpl implements UpdateParkingGarageUseCase {
    private final ParkingGarageRepository parkingGarageRepository;
    @Override
    public UpdateParkingGarageResponse updateParkingGarage(UpdateParkingGarageRequest request)
    {
        if (!parkingGarageRepository.existsById(request.getId())) {
            throw new InvalidParkingGarageExeption("PARKING_GARAGE_ID_INVALID");
        }

        Optional<ParkingGarageEntity> parkingGarageOptional = parkingGarageRepository.findById(request.getId());
        ParkingGarageEntity parkingGarage = parkingGarageOptional.get();
        updateFields(request, parkingGarage);
        return UpdateParkingGarageResponse.builder()
                .id(parkingGarage.getId())
                .build();

    }

    private void updateFields(UpdateParkingGarageRequest request, ParkingGarageEntity parkingGarage) {
        parkingGarage.setLocation(request.getLocation());
        parkingGarage.setName(request.getName());
        parkingGarage.setAirport(request.getAirport());
        parkingGarage.setTravelDistance(request.getTravelDistance());
        parkingGarage.setTravelTime(request.getTravelTime());
        parkingGarage.setPhoneNumber(request.getPhoneNumber());
        parkingGarage.setParkingGarageUtility(request.getParkingGarageUtility());
        parkingGarageRepository.save(parkingGarage);
    }
}
