package com.rooshdashboard.rooshdashboard.tests.business;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.rooshdashboard.rooshdashboard.business.exception.InvalidLocationException;
import com.rooshdashboard.rooshdashboard.business.impl.location.*;
import com.rooshdashboard.rooshdashboard.domain.location.*;
import com.rooshdashboard.rooshdashboard.persistance.LocationRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.LocationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class LocationTests {

    @Mock
    private LocationRepository mockLocationRepository;

    @InjectMocks
    private CreateLocationUseCaseImpl createLocationUseCase;
    @InjectMocks
    private UpdateLocationUseCaseImpl updateLocationUseCase;
    @InjectMocks
    private DeleteLocationUseCaseImpl deleteLocationUseCase;

    @InjectMocks
    private GetAllLocationsUseCaseImpl getLocationsUseCase;
    @InjectMocks
    private GetLocationByIdUseCaseImpl getLocationByIdUseCase;

    @Test
    public void testGetLocationByIdWithExistingLocation() {
        // Arrange
        Long validId = 1L;
        LocationEntity locationEntity = LocationEntity.builder()
                .id(validId)
                .parkingSlot(100)
                .floor(1)
                .build();

        Location location = LocationConverter.convert(locationEntity);
        when(mockLocationRepository.existsById(validId)).thenReturn(true);
        when(mockLocationRepository.findById(validId)).thenReturn(Optional.of(locationEntity));

        // Act
        Location response = getLocationByIdUseCase.getLocation(validId).get();

        // Assert
        assertNotNull(response);
        assertEquals(location, response);
        verify(mockLocationRepository).findById(validId);
        verify(mockLocationRepository).existsById(validId);
    }

    @Test
    public void testCreateLocationWithValidRequest() {
        // Arrange
        CreateLocationRequest validRequest = CreateLocationRequest.builder()
                .locationId(null)
                .floor(1)
                .parkingSlot(100)
                .build();

        LocationEntity savedLocation = LocationEntity.builder()
                .id(1L)
                .parkingSlot(100)
                .floor(1)
                .build();
        when(mockLocationRepository.save(any(LocationEntity.class))).thenReturn(savedLocation);

        // Act
        CreateLocationResponse response = createLocationUseCase.createLocation(validRequest);

        // Assert
        assertNotNull(response);
        assertEquals(savedLocation.getId(), response.getLocationId());
        verify(mockLocationRepository).save(any(LocationEntity.class));
    }

//    @Test
//    public void testDeleteLocationWithValidId() {
//        // Arrange
//        Long validId = 1L;
//        when(mockLocationRepository.existsById(validId)).thenReturn(true);
//
//        // Act
//        DeleteLocationResponse response = deleteLocationUseCase.deleteLocation(validId);
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(validId, response.getLocationId());
//        verify(mockLocationRepository).existsById(validId);
//        verify(mockLocationRepository).deleteById(validId);
//    }

    @Test
    public void testDeleteLocationWithInvalidId() {
        // Arrange
        Long invalidId = 99L;
        when(mockLocationRepository.existsById(invalidId)).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidLocationException.class, () -> deleteLocationUseCase.deleteLocation(invalidId));
        verify(mockLocationRepository).existsById(invalidId);
        verify(mockLocationRepository, never()).deleteById(invalidId);
    }

//    @Test
//    public void testUpdateLocationWithValidRequest() {
//        // Arrange
//        Long validLocationId = 1L;
//        UpdateLocationRequest request = UpdateLocationRequest.builder()
//                .id(validLocationId)
//                .floor(1)
//                .parkingSlot(100)
//                .build();
//
//        when(mockLocationRepository.existsById(validLocationId)).thenReturn(true);
//
//        // Act
//        UpdateLocationResponse response = updateLocationUseCase.updateLocation(request);
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(validLocationId, response.getLocationId());
//        verify(mockLocationRepository).save(any(LocationEntity.class));
//    }

    @Test
    public void testUpdateLocationWithInvalidId() {
        // Arrange
        Long invalidLocationId = 99L;
        UpdateLocationRequest invalidRequest = UpdateLocationRequest.builder()
                .id(invalidLocationId)
                .floor(1)
                .parkingSlot(100)
                .build();

        // Act & Assert
        assertThrows(InvalidLocationException.class, () -> updateLocationUseCase.updateLocation(invalidRequest));
        verify(mockLocationRepository, never()).save(any(LocationEntity.class));
    }

    @Test
    public void testGetAllLocationsWithEmptyRepository() {
        // Arrange
        when(mockLocationRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        GetAllLocationsResponse response = getLocationsUseCase.getLocations();

        // Assert
        assertNotNull(response);
        assertTrue(response.getLocationsList().isEmpty());
    }

    @Test
    public void testGetAllLocationsWithFilledRepository() {
        // Arrange
        List<LocationEntity> locationEntities = List.of(
                LocationEntity.builder().id(1L).parkingSlot(100).floor(1).build(),
                LocationEntity.builder().id(2L).parkingSlot(150).floor(2).build()
        );
        when(mockLocationRepository.findAll()).thenReturn(locationEntities);

        // Act
        GetAllLocationsResponse response = getLocationsUseCase.getLocations();

        // Assert
        assertNotNull(response);
        assertEquals(2, response.getLocationsList().size());
    }

    @Test
    public void testGetLocationByIdWithNonExistingLocation() {
        // Arrange
        Long nonExistentId = 999L;
        when(mockLocationRepository.existsById(nonExistentId)).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidLocationException.class, () -> getLocationByIdUseCase.getLocation(nonExistentId));
        verify(mockLocationRepository, never()).findById(nonExistentId);
        verify(mockLocationRepository).existsById(nonExistentId);
    }
}
