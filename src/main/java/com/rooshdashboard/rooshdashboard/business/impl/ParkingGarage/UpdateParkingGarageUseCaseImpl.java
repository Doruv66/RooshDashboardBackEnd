package com.rooshdashboard.rooshdashboard.business.impl.ParkingGarage;

import com.rooshdashboard.rooshdashboard.business.IParkingGarage.UpdateParkingGarageUseCase;
import com.rooshdashboard.rooshdashboard.domain.ParkingGaragee.UpdateParkingGarageRequest;
import com.rooshdashboard.rooshdashboard.domain.ParkingGaragee.UpdateParkingGarageResponse;
import com.rooshdashboard.rooshdashboard.persistance.ParkingGarageRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidParkingGarageExeption;
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
        Optional<ParkingGarageEntity> parkingGarageOptional = parkingGarageRepository.findById(request.getId());

        if (!parkingGarageRepository.existsById(request.getId())) {
            throw new InvalidParkingGarageExeption("PARKING_GARAGE_ID_INVALID");
        }


        ParkingGarageEntity parkingGarage = parkingGarageOptional.get();
        updateFields(request, parkingGarage);
        return UpdateParkingGarageResponse.builder()
                .message("Parking Garage " + parkingGarage.getId() + " Has been updated!")
                .build();

    }

    private void updateFields(UpdateParkingGarageRequest request, ParkingGarageEntity parkingGarage) {
        parkingGarage.setAddress(request.getAddress());
        parkingGarage.setBookingId(request.getBookingId());
        parkingGarageRepository.save(parkingGarage);
    }
}
