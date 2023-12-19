package com.rooshdashboard.rooshdashboard.business.impl.ParkingGarage;

import com.rooshdashboard.rooshdashboard.business.IParkingGarage.DeleteParkingGarageUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidParkingGarageExeption;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.DeleteParkingGarageResponse;
import com.rooshdashboard.rooshdashboard.persistance.ParkingGarageRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteParkingGarageUseCaseImpl implements DeleteParkingGarageUseCase {
    private final ParkingGarageRepository parkingGarageRepository;

    @Override
    public DeleteParkingGarageResponse deleteParkingGarage(Long id) {
        if (!this.parkingGarageRepository.existsById(id)) {
            throw new InvalidParkingGarageExeption("PARKING_GARAGE_NOT_FOUND");
        } else {
            Optional<ParkingGarageEntity> parkingGarageOptional = this.parkingGarageRepository.findById(id);

            if (parkingGarageOptional.isPresent()) {
                ParkingGarageEntity parkingGarage = parkingGarageOptional.get();
                List<String> imagePaths = parkingGarage.getImagePaths();

                if (imagePaths != null) {
                    for (String imagePath : imagePaths) {
                        try {
                            String decodedPath = URLDecoder.decode(imagePath, StandardCharsets.UTF_8);
                            Path filePath = Paths.get(decodedPath);
                            Files.deleteIfExists(filePath);
                        } catch (IOException e) {
                            // Handle file deletion error
                        }
                    }
                }

                this.parkingGarageRepository.deleteById(id);
            }

            return DeleteParkingGarageResponse.builder()
                    .message("Garage " + id + " has been deleted!")
                    .build();
        }
    }
}
