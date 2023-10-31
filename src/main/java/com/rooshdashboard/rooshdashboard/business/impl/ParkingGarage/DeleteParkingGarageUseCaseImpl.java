package com.rooshdashboard.rooshdashboard.business.impl.ParkingGarage;

import com.rooshdashboard.rooshdashboard.business.IParkingGarage.DeleteParkingGarageUseCase;
import com.rooshdashboard.rooshdashboard.domain.ParkingGaragee.DeleteParkingGarageResponse;
import com.rooshdashboard.rooshdashboard.persistance.ParkingGarageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteParkingGarageUseCaseImpl implements DeleteParkingGarageUseCase {
    private final ParkingGarageRepository parkingGarageRepository;

    @Override
    public DeleteParkingGarageResponse deleteParkingGarage(int id)
    {
        if (!this.parkingGarageRepository.existsById(id))
        {
            return DeleteParkingGarageResponse.builder()
                    .message("No job with id: " + id)
                    .build();
        }
        else {
            this.parkingGarageRepository.deleteById((id));
            return DeleteParkingGarageResponse.builder()
                    .message("Garage " + id + " Has been deleted!")
                    .build();
        }
    }
}
