package com.rooshdashboard.rooshdashboard.business.impl.ParkingGarage;

import com.rooshdashboard.rooshdashboard.business.IParkingGarage.GetParkingGarageByIdUseCase;
import com.rooshdashboard.rooshdashboard.persistance.ParkingGarageRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.rooshdashboard.rooshdashboard.domain.ParkingGaragee.ParkingGarage;


@Service
@AllArgsConstructor
public class GetParkingGarageByIdUseCaseImpl implements GetParkingGarageByIdUseCase {
    private final ParkingGarageRepository parkingGarageRepository;

    @Override
    public Optional<ParkingGarage> getParkingGarageById(int id) {
        return parkingGarageRepository.findById(id).map(ParkingGarageConverter::convert);
    }
}
