package com.rooshdashboard.rooshdashboard.tests.business;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.rooshdashboard.rooshdashboard.business.exception.InvalidDataException;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidParkingGarageExeption;
import com.rooshdashboard.rooshdashboard.business.impl.ParkingGarage.*;
import com.rooshdashboard.rooshdashboard.configuration.security.token.AccessToken;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.*;
import com.rooshdashboard.rooshdashboard.persistance.ParkingGarageRepository;
import com.rooshdashboard.rooshdashboard.persistance.UserRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;

import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageUtilityEntity;
import com.rooshdashboard.rooshdashboard.persistance.entity.RoleEntity;
import com.rooshdashboard.rooshdashboard.persistance.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class ParkingGarageTests {

    @Mock
    private ParkingGarageRepository mockParkingGarageRepository;
    @Mock
    private AccessToken accessToken;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private CreateParkingGarageUseCaseImpl createParkingGarageUseCase;
    @InjectMocks
    private UpdateParkingGarageUseCaseImpl updateParkingGarageUseCase;
    @InjectMocks
    private DeleteParkingGarageUseCaseImpl deleteParkingGarageUseCase;

    @InjectMocks
    private GetParkingGarageUseCaseImpl getParkingGaragesUseCase;
    @InjectMocks
    private GetParkingGarageByIdUseCaseImpl getParkingGarageByIdUseCase;

    @Test
    public void testGetParkingGaragesUseCase_withFilledRepository(){
        // Arrange
        UserEntity fakeAccount = UserEntity.builder().id(1L).userRoles(new HashSet<>()).build();
        ParkingGarageUtilityEntity fakeUtil = new ParkingGarageUtilityEntity();
        List<ParkingGarageEntity> parkingGarageEntityList = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            ParkingGarageEntity parkingGarageEntity = ParkingGarageEntity.builder()
                    .id((long) i+1)
                    .account(fakeAccount)
                    .parkingGarageUtility(fakeUtil)
                    .location("123 Main St")
                    .build();
            parkingGarageEntityList.add(parkingGarageEntity);
        }

        when(mockParkingGarageRepository.findByAccount_Id(1L)).thenReturn(parkingGarageEntityList);
        when(accessToken.getAccountId()).thenReturn(1L);

        // Act
        GetParkingGarageResponse response = getParkingGaragesUseCase.getParkingGarage();

        // Assert
        assertNotNull(response);
        assertEquals(parkingGarageEntityList.size(), response.getParkingGarages().size());
        verify(mockParkingGarageRepository).findByAccount_Id(anyLong());
    }

    @Test
    public void testGetParkingGaragesUseCase_withEmptyRepository(){
        // Arrange
        List<ParkingGarageEntity> parkingGarageEntityList = new ArrayList<>();

        when(mockParkingGarageRepository.findByAccount_Id(1L)).thenReturn(parkingGarageEntityList);
        when(accessToken.getAccountId()).thenReturn(1L);
        // Act
        GetParkingGarageResponse response = getParkingGaragesUseCase.getParkingGarage();

        // Assert
        assertNotNull(response);
        assertEquals(parkingGarageEntityList.size(), response.getParkingGarages().size());
        verify(mockParkingGarageRepository).findByAccount_Id(anyLong());
    }

    @Test
    public void testGetParkingGarageByIdWithExistingParkingGarage() {
        // Arrange
        Long validId = 1L;
        RoleEntity fakeRole = new RoleEntity();
        UserEntity fakeAccount = UserEntity.builder().id(1L).userRoles(new HashSet<>()).build();
        ParkingGarageUtilityEntity fakeUtil = new ParkingGarageUtilityEntity();

        ParkingGarageEntity parkingGarageEntity = ParkingGarageEntity.builder()
                .id(validId)
                .account(fakeAccount)
                .parkingGarageUtility(fakeUtil)
                .location("123 Main St")
                .build();

        ParkingGarage parkingGarage = ParkingGarageConverter.convert(parkingGarageEntity);
        when(mockParkingGarageRepository.existsById(anyLong())).thenReturn(true);
        when(mockParkingGarageRepository.findById(validId)).thenReturn(Optional.of(parkingGarageEntity));

        // Act
        ParkingGarage response = getParkingGarageByIdUseCase.getParkingGarageById(validId).get();

        // Assert
        assertNotNull(response);
        assertEquals(parkingGarage, response);
        verify(mockParkingGarageRepository).findById(validId);
        verify(mockParkingGarageRepository).existsById(validId);
    }

    @Test
    public void testCreateParkingGarageWithValidRequest() {
        // Arrange
        CreateParkingGarageRequest validRequest = CreateParkingGarageRequest.builder()
                .airport("air")
                .name("name")
                .parkingGarageUtility(ParkingGarageUtilityEntity.builder().build())
                .travelTime(12L)
                .phoneNumber("2345676543")
                .travelDistance(12L)
                .location("123 Main St")
                .userId(1L)
                .build();
        UserEntity fakeAccount = UserEntity.builder().id(1L).userRoles(new HashSet<>()).build();
        ParkingGarageEntity savedGarage = ParkingGarageEntity.builder()
                .id(1L)
                .location("123 Main St")
                .build();
        when(mockParkingGarageRepository.save(any(ParkingGarageEntity.class))).thenReturn(savedGarage);
        when(userRepository.getReferenceById(1L)).thenReturn(fakeAccount);

        // Act
        CreateParkingGarageResponse response = createParkingGarageUseCase.CreateParkingGarage(validRequest);

        // Assert
        assertNotNull(response);
        assertEquals(savedGarage.getId(), response.getId());
        verify(mockParkingGarageRepository).save(any(ParkingGarageEntity.class));
    }

    @Test
    public void testDeleteParkingGarageWithValidId() {
        // Arrange
        Long validId = 1L;
        when(mockParkingGarageRepository.existsById(validId)).thenReturn(true);
        when(mockParkingGarageRepository.findById(1L)).thenReturn(Optional.of(ParkingGarageEntity.builder().build()));

        // Act
        DeleteParkingGarageResponse response = deleteParkingGarageUseCase.deleteParkingGarage(validId);

        // Assert
        assertNotNull(response);
        assertEquals("Garage 1 has been deleted!", response.getMessage());
        verify(mockParkingGarageRepository).existsById(validId);
        verify(mockParkingGarageRepository).deleteById(validId);
    }

    @Test
    public void testUpdateParkingGarageWithValidRequest() {
        // Arrange
        Long validGarageId = 1L;
        RoleEntity fakeRole = new RoleEntity();
        UserEntity fakeAccount = UserEntity.builder().build();
        ParkingGarageUtilityEntity fakeUtil = new ParkingGarageUtilityEntity();
        UpdateParkingGarageRequest validRequest = UpdateParkingGarageRequest.builder()
                .id(1L)
                .airport("air")
                .name("name")
                .parkingGarageUtility(ParkingGarageUtilityEntity.builder().build())
                .travelTime(12L)
                .phoneNumber("2345676543")
                .travelDistance(12L)
                .location("123 Main St")
                .build();
        ParkingGarageEntity existingGarage = ParkingGarageEntity.builder()
                .id(validGarageId)
                .parkingGarageUtility(fakeUtil)
                .account(fakeAccount)
                .location("123 Main St")
                .imagePaths(new ArrayList<>())
                .build();
        when(mockParkingGarageRepository.save(any(ParkingGarageEntity.class))).thenReturn(existingGarage);
        when(mockParkingGarageRepository.existsById(validGarageId)).thenReturn(true);
        when(mockParkingGarageRepository.findById(validGarageId)).thenReturn(Optional.of(existingGarage));


        // Act
        UpdateParkingGarageResponse response = updateParkingGarageUseCase.updateParkingGarage(validRequest);

        // Assert
        assertNotNull(response);
        assertEquals(validRequest.getId(), response.getId());
        verify(mockParkingGarageRepository).existsById(validGarageId);
        verify(mockParkingGarageRepository).findById(validGarageId);
    }
    @Test
    public void testGetParkingGarageByIdWithNonExistingParkingGarage() {
        // Arrange
        Long nonExistentId = 999L;
        when(mockParkingGarageRepository.existsById(nonExistentId)).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidParkingGarageExeption.class, () -> getParkingGarageByIdUseCase.getParkingGarageById(nonExistentId));
        verify(mockParkingGarageRepository, never()).findById(nonExistentId);
        verify(mockParkingGarageRepository).existsById(nonExistentId);
    }

    @Test
    public void testGetParkingGarageByIdWithInvalidId() {
        // Arrange
        Long invalidId = 999L;
        when(mockParkingGarageRepository.existsById(invalidId)).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidParkingGarageExeption.class, () -> deleteParkingGarageUseCase.deleteParkingGarage(invalidId));
        verify(mockParkingGarageRepository, never()).findById(invalidId);
        verify(mockParkingGarageRepository).existsById(invalidId);
    }

    @Test
    public void testUpdateParkingGarageWithInvalidId() {
        // Arrange
        Long invalidGarageId = 99L;
        ParkingGarageUtilityEntity parkingGarageUtility = new ParkingGarageUtilityEntity(2L, false, false, 2L, 120L, 2L);
        UpdateParkingGarageRequest requestWithInvalidId = new UpdateParkingGarageRequest(invalidGarageId, "Fake Address", "Eindhoven Airport", "Eindhoven", 10L, 10L, "0612345678", parkingGarageUtility, new ArrayList<>(), new ArrayList<>());
        when(mockParkingGarageRepository.existsById(invalidGarageId)).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidParkingGarageExeption.class, () -> updateParkingGarageUseCase.updateParkingGarage(requestWithInvalidId));
        verify(mockParkingGarageRepository).existsById(invalidGarageId);
        verify(mockParkingGarageRepository, never()).findById(invalidGarageId);
   }

    @Test
    public void testDeleteParkingGarageWithInvalidId() {
        // Arrange
        Long invalidId = 99L;
        when(mockParkingGarageRepository.existsById(invalidId)).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidParkingGarageExeption.class, () -> deleteParkingGarageUseCase.deleteParkingGarage(invalidId));
        verify(mockParkingGarageRepository).existsById(invalidId);
        verify(mockParkingGarageRepository, never()).deleteById(invalidId);
    }

    @Test
    public void testCreateParkingGarageWithInvalidRequest() {
        // Arrange
        CreateParkingGarageRequest request = CreateParkingGarageRequest.builder().build();

        // Act & Assert
        assertThrows(InvalidDataException.class, () -> createParkingGarageUseCase.CreateParkingGarage(request));
        verify(mockParkingGarageRepository, never()).save(any(ParkingGarageEntity.class));
    }
}
