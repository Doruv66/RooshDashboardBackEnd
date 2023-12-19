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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CreateParkingGarageUseCaseImpl implements CreateParkingGarageUseCase {
    private final ParkingGarageRepository parkingGarageRepository;
    private final AccountRepository accountRepository;

    @Override
    public CreateParkingGarageResponse CreateParkingGarage(CreateParkingGarageRequest request)
    {
            ParkingGarageEntity parkingGarage = saveNewParkingGarage(request);
            return CreateParkingGarageResponse.builder()
                    .id(parkingGarage.getId())
                    .build();
    }

    private ParkingGarageEntity saveNewParkingGarage(CreateParkingGarageRequest request) {
        Map<String, String> errors = new HashMap<>();
        List<String> imageFilePaths = new ArrayList<>();
        try {
            String uploadDir = "uploaded-images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            for (MultipartFile image : request.getImages()) {
                String fileName = StringUtils.cleanPath(image.getOriginalFilename());

                String fileExtension = "";
                int lastDot = fileName.lastIndexOf('.');
                if (lastDot > 0) {
                    fileExtension = fileName.substring(lastDot);
                    fileName = fileName.substring(0, lastDot);
                }
                String uniqueFileName = fileName + "_" + System.currentTimeMillis() + fileExtension;
                Path filePath = uploadPath.resolve(uniqueFileName);

                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                    String encodedFileName = URLEncoder.encode(uniqueFileName, StandardCharsets.UTF_8).replace("+", "%20");
                    imageFilePaths.add(uploadDir + encodedFileName);
                }
            }
        } catch (IOException e) {
            errors.put("image", "Unable to save the image files.");;
        }
//        AccountEntity foundAccount = accountRepository.getReferenceById(request.getAccountId());
        ParkingGarageEntity newParkingGarage = ParkingGarageEntity.builder()
                .location(request.getLocation())
                .name(request.getName())
//                .account(foundAccount)
                .parkingGarageUtility(request.getParkingGarageUtility())
                .travelDistance(request.getTravelDistance())
                .travelTime(request.getTravelTime())
                .location(request.getLocation())
                .phoneNumber(request.getPhoneNumber())
                .airport(request.getAirport())
                .imagePaths(imageFilePaths)
                .build();
        return parkingGarageRepository.save(newParkingGarage);
    }
}
