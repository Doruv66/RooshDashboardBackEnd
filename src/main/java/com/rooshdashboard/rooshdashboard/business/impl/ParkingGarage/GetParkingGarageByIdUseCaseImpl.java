package com.rooshdashboard.rooshdashboard.business.impl.ParkingGarage;

import com.rooshdashboard.rooshdashboard.business.IParkingGarage.GetParkingGarageByIdUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidParkingGarageExeption;
import com.rooshdashboard.rooshdashboard.domain.ParkingGaragee.ParkingGarage;
import com.rooshdashboard.rooshdashboard.persistance.ParkingGarageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class GetParkingGarageByIdUseCaseImpl implements GetParkingGarageByIdUseCase {
    private final ParkingGarageRepository parkingGarageRepository;

    @Override
    public Optional<ParkingGarage> getParkingGarageById(int id) {
        if(!parkingGarageRepository.existsById(id)){
            throw new InvalidParkingGarageExeption("PARKINGGARAGE_ID_INVALID");
        }
        return parkingGarageRepository.findById(id).map(ParkingGarageConverter::convert);
    }
}
