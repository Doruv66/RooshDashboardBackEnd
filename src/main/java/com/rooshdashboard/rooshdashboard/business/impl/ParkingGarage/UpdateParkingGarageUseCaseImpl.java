package com.rooshdashboard.rooshdashboard.business.impl.ParkingGarage;

import com.rooshdashboard.rooshdashboard.business.IParkingGarage.UpdateParkingGarageUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidDataException;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidParkingGarageExeption;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.CreateParkingGarageRequest;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.UpdateParkingGarageRequest;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.UpdateParkingGarageResponse;
import com.rooshdashboard.rooshdashboard.persistance.ParkingGarageRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
@AllArgsConstructor
public class UpdateParkingGarageUseCaseImpl implements UpdateParkingGarageUseCase {
    private final ParkingGarageRepository parkingGarageRepository;
    @Override
    @Transactional
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
        List<String> existingImageFilePaths = new ArrayList<>(parkingGarage.getImagePaths());
        List<String> newImageFilePaths = new ArrayList<>();
        Map<String, String> errors = validateCreateParkingGarageRequest(request);
        try {
            String uploadDir = "uploaded-images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            if(!(request.getImages() == null)){
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
                        newImageFilePaths.add(uploadDir + encodedFileName);
                    }
                }
            }

        } catch (IOException e) {
            errors.put("image", "Unable to save the image files.");
        }
        existingImageFilePaths.addAll(newImageFilePaths);
        if (request.getImagesToRemove() != null) {
            for (String imagePath : request.getImagesToRemove()) {
                existingImageFilePaths.removeIf(path -> path.endsWith(imagePath));
                String decodedPath = URLDecoder.decode(imagePath, StandardCharsets.UTF_8);
                Path filePath = Paths.get(decodedPath);
                try {
                    Files.deleteIfExists(filePath);
                } catch (IOException e) {
                    errors.put("image", "Unable to delete removed images.");
                }
            }
        }
        if (!errors.isEmpty()) {
            throw new InvalidDataException(errors);
        }
        parkingGarage.setLocation(request.getLocation());
        parkingGarage.setName(request.getName());
        parkingGarage.setAirport(request.getAirport());
        parkingGarage.setTravelDistance(request.getTravelDistance());
        parkingGarage.setTravelTime(request.getTravelTime());
        parkingGarage.setPhoneNumber(request.getPhoneNumber());
        parkingGarage.setParkingGarageUtility(request.getParkingGarageUtility());
        parkingGarage.setImagePaths(existingImageFilePaths);
        parkingGarageRepository.save(parkingGarage);
    }
    private Map<String, String> validateCreateParkingGarageRequest(UpdateParkingGarageRequest request) {
        Map<String, String> errors = new HashMap<>();

        if (request.getName() == null || request.getName().isBlank()) {
            errors.put("name", "Name cannot be blank");
        } else if (request.getName().length() > 255) {
            errors.put("name", "Name must not exceed 255 characters");
        }

        if (request.getAirport() == null || request.getAirport().isBlank()) {
            errors.put("airport", "Airport cannot be blank");
        } else if (request.getAirport().length() > 255) {
            errors.put("airport", "Airport must not exceed 255 characters");
        }

        if (request.getLocation() == null || request.getLocation().isBlank()) {
            errors.put("location", "Location cannot be blank");
        } else if (request.getLocation().length() > 255) {
            errors.put("location", "Location must not exceed 255 characters");
        }

        if (request.getTravelTime() == null) {
            errors.put("travelTime", "Travel time cannot be null");
        }

        if (request.getTravelDistance() == null) {
            errors.put("travelDistance", "Travel distance cannot be null");
        }

        if (request.getPhoneNumber() == null || request.getPhoneNumber().isBlank()) {
            errors.put("phoneNumber", "Phone number cannot be blank");
        }

        if (request.getParkingGarageUtility() == null) {
            errors.put("parkingGarageUtility", "Parking garage utility cannot be null");
        }

        if (!isImage(request.getImages())) {
            errors.put("images", "Please make sure the images are valid.");
        }

        return errors;
    }
    private boolean isImage(List<MultipartFile> images) {
        for(MultipartFile file : images){
            if (file != null) {
                String contentType = file.getContentType();
                return contentType != null && contentType.startsWith("image/");
            }
            return false;
        }
        return true;
    }
}
