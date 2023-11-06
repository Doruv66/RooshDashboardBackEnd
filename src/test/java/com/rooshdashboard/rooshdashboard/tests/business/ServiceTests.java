package com.rooshdashboard.rooshdashboard.tests.business;

import static com.rooshdashboard.rooshdashboard.domain.service.ServiceType.Valet;
import static com.rooshdashboard.rooshdashboard.persistance.entity.ServiceType.Shuttle;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.rooshdashboard.rooshdashboard.business.exception.InvalidServiceException;
import com.rooshdashboard.rooshdashboard.business.impl.service.*;
import com.rooshdashboard.rooshdashboard.domain.service.*;
import com.rooshdashboard.rooshdashboard.persistance.ServiceRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.ServiceEntity;
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

@ExtendWith(MockitoExtension.class)
public class ServiceTests {

    @Mock
    private ServiceRepository mockServiceRepository;

    @InjectMocks
    private CreateServiceUseCaseImpl createServiceUseCase;
    @InjectMocks
    private UpdateServiceUseCaseImpl updateServiceUseCase;
    @InjectMocks
    private DeleteServiceUseCaseImpl deleteServiceUseCase;
    @InjectMocks
    private GetAllServicesUseCaseImpl getAllServicesUseCase;
    @InjectMocks
    private GetServiceByIdUseCaseImpl getServiceByIdUseCase;

    @Test
    public void testCreateServiceWithValidRequest() {
        // Happy Flow
        CreateServiceRequest validRequest = new CreateServiceRequest( com.rooshdashboard.rooshdashboard.persistance.entity.ServiceType.Valet);
        ServiceEntity savedService = ServiceEntity.builder().id(1L).serviceType(com.rooshdashboard.rooshdashboard.persistance.entity.ServiceType.Valet).build();
        when(mockServiceRepository.save(any(ServiceEntity.class))).thenReturn(savedService);

        // Act
        CreateServiceResponse response = createServiceUseCase.createService(validRequest);

        // Assert
        assertNotNull(response);
        assertEquals(savedService.getId(), response.getId());
        verify(mockServiceRepository).save(any(ServiceEntity.class));
    }

    @Test
    public void testCreateServiceWithInvalidRequest() {
        // Sad Flow
        CreateServiceRequest invalidRequest = null;

        // Act & Assert
        assertThrows(InvalidServiceException.class, () -> createServiceUseCase.createService(invalidRequest));
    }

    @Test
    public void testUpdateServiceWithValidRequest() {
        // Happy Flow
        long validServiceId = 1L;
        UpdateServiceRequest validRequest = new UpdateServiceRequest(validServiceId, 150.0, Shuttle);
        ServiceEntity existingService = ServiceEntity.builder().id(validServiceId).serviceType(com.rooshdashboard.rooshdashboard.persistance.entity.ServiceType.Valet).build();
        ServiceEntity updatedService = ServiceEntity.builder().id(validServiceId).serviceType(Shuttle).build();

        when(mockServiceRepository.findById(validServiceId)).thenReturn(Optional.of(existingService));
        when(mockServiceRepository.existsById(validServiceId)).thenReturn(true);
        when(mockServiceRepository.save(any(ServiceEntity.class))).thenReturn(updatedService);

        // Act
        UpdateServiceResponse response = updateServiceUseCase.updateService(validRequest);

        // Assert
        assertNotNull(response);
        assertEquals(validServiceId, response.getId());
        verify(mockServiceRepository).existsById(validServiceId);
        verify(mockServiceRepository).findById(validServiceId);
    }

    @Test
    public void testUpdateServiceWithInvalidId() {
        // Sad Flow
        long invalidServiceId = 99L;
        UpdateServiceRequest requestWithInvalidId = new UpdateServiceRequest(invalidServiceId, 150.0, com.rooshdashboard.rooshdashboard.persistance.entity.ServiceType.Valet);
        when(mockServiceRepository.existsById(invalidServiceId)).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidServiceException.class, () -> updateServiceUseCase.updateService(requestWithInvalidId));
        verify(mockServiceRepository).existsById(invalidServiceId);
    }

    @Test
    public void testDeleteServiceWithValidId() {
        // Happy Flow
        Long validId = 1L;
        when(mockServiceRepository.existsById(validId)).thenReturn(true);

        // Act
        DeleteServiceResponse response = deleteServiceUseCase.deleteService(validId);

        // Assert
        assertNotNull(response);
        assertEquals(validId, response.getId());
        verify(mockServiceRepository).existsById(validId);
        verify(mockServiceRepository).deleteById(validId);
    }

    @Test
    public void testDeleteServiceWithInvalidId() {
        // Sad Flow
        Long invalidId = 99L;
        when(mockServiceRepository.existsById(invalidId)).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidServiceException.class, () -> deleteServiceUseCase.deleteService(invalidId));
        verify(mockServiceRepository).existsById(invalidId);
        verify(mockServiceRepository, never()).deleteById(invalidId);
    }

    @Test
    public void testGetAllServicesWithEmptyRepository() {
        // Happy Flow
        when(mockServiceRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        GetAllServicesResponse response = getAllServicesUseCase.getAllServices();

        // Assert
        assertNotNull(response);
        assertTrue(response.getServices().isEmpty());
    }

    @Test
    public void testGetAllServicesWithFilledRepository() {
        // Sad Flow
        List<ServiceEntity> serviceEntities = IntStream.range(1, 6).mapToObj(i -> ServiceEntity.builder().id((long) i).
                serviceType(com.rooshdashboard.rooshdashboard.persistance.entity.ServiceType.Valet).build()).collect(Collectors.toList());
        when(mockServiceRepository.findAll()).thenReturn(serviceEntities);

        // Act
        GetAllServicesResponse response = getAllServicesUseCase.getAllServices();

        // Assert
        assertNotNull(response);
        assertEquals(5, response.getServices().size());
    }

    @Test
    public void testGetServiceByIdWithExistingService() {
        // Happy Flow
        Long validId = 1L;
        ServiceEntity serviceEntity = ServiceEntity.builder().id(validId)
                .serviceType(com.rooshdashboard.rooshdashboard.persistance.entity.ServiceType.Valet).build();
        Service service = ServiceConverter.convert(serviceEntity);
        when(mockServiceRepository.existsById(validId)).thenReturn(true);
        when(mockServiceRepository.findById(validId)).thenReturn(Optional.of(serviceEntity));

        // Act
        Service response = getServiceByIdUseCase.getServiceById(validId);

        // Assert
        assertNotNull(response);
        assertEquals(service, response);
        verify(mockServiceRepository).findById(validId);
        verify(mockServiceRepository).existsById(validId);
    }

    @Test
    public void testGetServiceByIdWithNonExistingService() {
        // Sad Flow
        Long nonExistentId = 999L;
        when(mockServiceRepository.existsById(nonExistentId)).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidServiceException.class, () -> getServiceByIdUseCase.getServiceById(nonExistentId));
        verify(mockServiceRepository, never()).findById(nonExistentId);
        verify(mockServiceRepository).existsById(nonExistentId);
    }

    @Test
    public void testGetServiceByIdRepositoryError() {
        // Sad Flow
        Long validId = 1L;
        when(mockServiceRepository.existsById(validId)).thenReturn(true);
        when(mockServiceRepository.findById(validId)).thenThrow(new RuntimeException("Database Error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> getServiceByIdUseCase.getServiceById(validId));
        verify(mockServiceRepository).findById(validId);
        verify(mockServiceRepository).existsById(validId);
    }
}
