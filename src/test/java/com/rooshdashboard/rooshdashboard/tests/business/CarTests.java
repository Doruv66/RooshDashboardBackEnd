package com.rooshdashboard.rooshdashboard.tests.business;

import com.rooshdashboard.rooshdashboard.business.exception.InvalidCarException;
import com.rooshdashboard.rooshdashboard.business.impl.car.*;
import com.rooshdashboard.rooshdashboard.domain.car.*;
import com.rooshdashboard.rooshdashboard.persistance.CarRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.CarEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarTests {

    @Mock
    private CarRepository mockCarRepository;

    @InjectMocks
    private CreateCarUseCaseImpl createCarUseCase;
    @InjectMocks
    private UpdateCarUseCaseImpl updateCarUseCase;
    @InjectMocks
    private DeleteCarUseCaseImpl deleteCarUseCase;
    @InjectMocks
    private GetAllCarsUseCaseImpl getAllCarsUseCase;
    @InjectMocks
    private GetCarByIdUseCaseImpl getCarByIdUseCase;

    @Test
    public void testGetCarByIdWithExistingCar() {
        // Arrange
        Long validId = 1L;
        CarEntity carEntity = CarEntity.builder().id(validId).licensePlate("XYZ123").brand("Toyota").model("Camry").electric(false).build();
        Car car = CarConverter.convert(carEntity);
        when(mockCarRepository.existsById(validId)).thenReturn(true);
        when(mockCarRepository.findById(validId)).thenReturn(Optional.of(carEntity));

        // Act
        GetCarByIdResponse response = getCarByIdUseCase.getCar(validId);

        // Assert
        assertNotNull(response);
        assertEquals(car, response.getCar());
        verify(mockCarRepository).findById(validId);
        verify(mockCarRepository).existsById(validId);
    }

    @Test
    public void testCreateCarWithValidRequest() {
        // Arrange
        CreateCarRequest validRequest = new CreateCarRequest("XYZ123", "Toyota", "Camry", false);
        CarEntity savedCar = CarEntity.builder().id(1L).licensePlate("XYZ123").brand("Toyota").model("Camry").electric(false).build();
        when(mockCarRepository.save(any(CarEntity.class))).thenReturn(savedCar);

        // Act
        CreateCarResponse response = createCarUseCase.createCar(validRequest);

        // Assert
        assertNotNull(response);
        assertEquals(savedCar.getId(), response.getId());
        verify(mockCarRepository).save(any(CarEntity.class));
    }

    @Test
    public void testDeleteCarWithValidId() {
        // Arrange
        Long validId = 1L;
        when(mockCarRepository.existsById(validId)).thenReturn(true);

        // Act
        DeleteCarResponse response = deleteCarUseCase.deleteCar(validId);

        // Assert
        assertNotNull(response);
        assertEquals(validId, response.getId());
        verify(mockCarRepository).existsById(validId);
        verify(mockCarRepository).deleteById(validId);
    }

    @Test
    public void testDeleteCarWithInvalidId() {
        // Arrange
        Long invalidId = 99L;
        when(mockCarRepository.existsById(invalidId)).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidCarException.class, () -> deleteCarUseCase.deleteCar(invalidId));
        verify(mockCarRepository).existsById(invalidId);
        verify(mockCarRepository, never()).deleteById(invalidId);
    }

    @Test
    public void testUpdateCarWithValidRequest() {
        // Arrange
        long validCarId = 1L;
        UpdateCarRequest validRequest = new UpdateCarRequest(validCarId, "XYZ124", "Toyota", "Camry", true);
        CarEntity existingCar = CarEntity.builder().id(validCarId).licensePlate("XYZ123").brand("Toyota").model("Camry").electric(false).build();
        when(mockCarRepository.existsById(validCarId)).thenReturn(true);
        when(mockCarRepository.findById(validCarId)).thenReturn(Optional.of(existingCar));

        // Act
        UpdateCarResponse response = updateCarUseCase.updateCar(validRequest);

        // Assert
        assertNotNull(response);
        assertEquals(validCarId, response.getId());
        verify(mockCarRepository).existsById(validCarId);
        verify(mockCarRepository).findById(validCarId);
    }

    @Test
    public void testUpdateCarWithInvalidId() {
        // Arrange
        long invalidCarId = 99L;
        UpdateCarRequest requestWithInvalidId = new UpdateCarRequest(invalidCarId, "XYZ999", "Toyota", "Avalon", true);
        when(mockCarRepository.existsById(invalidCarId)).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidCarException.class, () -> updateCarUseCase.updateCar(requestWithInvalidId));
        verify(mockCarRepository).existsById(invalidCarId);
        verify(mockCarRepository, never()).findById(invalidCarId);
    }

    @Test
    public void testGetAllCarsWithEmptyRepository() {
        // Arrange
        when(mockCarRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        GetAllCarsResponse response = getAllCarsUseCase.getCars();

        // Assert
        assertNotNull(response);
        assertEquals(0, response.getCars().size());
    }

    @Test
    public void testGetAllCarsWithFilledRepository() {
        // Arrange
        List<CarEntity> carEntities = IntStream.range(1, 6).mapToObj(i -> CarEntity.builder().id((long) i).licensePlate("XYZ" + i).brand("Toyota").model("Model" + i).electric(i % 2 == 0).build()).collect(Collectors.toList());
        when(mockCarRepository.findAll()).thenReturn(carEntities);

        // Act
        GetAllCarsResponse response = getAllCarsUseCase.getCars();

        // Assert
        assertNotNull(response);
        assertEquals(5, response.getCars().size());
    }

    @Test
    public void testGetCarByIdWithNonExistingCar() {
        // Arrange
        Long nonExistentId = 999L;
        when(mockCarRepository.existsById(nonExistentId)).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidCarException.class, () -> getCarByIdUseCase.getCar(nonExistentId));
        verify(mockCarRepository, never()).findById(nonExistentId);
        verify(mockCarRepository).existsById(nonExistentId);
    }

    @Test
    public void testGetCarByIdRepositoryError() {
        // Arrange
        Long validId = 1L;
        when(mockCarRepository.existsById(validId)).thenReturn(true);
        when(mockCarRepository.findById(validId)).thenThrow(new RuntimeException("Database Error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> getCarByIdUseCase.getCar(validId));
        verify(mockCarRepository).findById(validId);
        verify(mockCarRepository).existsById(validId);
    }
}
