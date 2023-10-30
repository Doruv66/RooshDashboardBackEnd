package com.rooshdashboard.rooshdashboard.tests.business;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import com.rooshdashboard.rooshdashboard.business.exception.InvalidCustomerException;
import com.rooshdashboard.rooshdashboard.business.impl.Customer.*;
import com.rooshdashboard.rooshdashboard.domain.Customer.*;
import com.rooshdashboard.rooshdashboard.persistance.CustomerRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CustomerTests {

    @Mock
    private CustomerRepository mockCustomerRepository;

    @InjectMocks
    private CreateCustomerUseCaseImpl createCustomerUseCase;
    @InjectMocks
    private UpdateCustomerUseCaseImpl updateCustomerUseCase;
    @InjectMocks
    private DeleteCustomerUseCaseImpl deleteCustomerUseCase;

//    @InjectMocks
//    private GetAllCustomersImpl getAllCustomersUseCase;
    @InjectMocks
    private GetCustomerByIdUseCaseImpl getCustomerByIdUseCase;

    @Test
    public void testGetCustomerByIdWithExistingCustomer() {
        // Arrange
        Long validId = 1L;
        CustomerEntity customerEntity = CustomerEntity.builder()
                .id(validId)
                .name("John Doe")
                .email("johndoe@example.com")
                .phoneNumber("123-456-7890")
                .build();

        Customer customer = CustomerConverter.convert(customerEntity);
        when(mockCustomerRepository.existsById(validId)).thenReturn(true);
        when(mockCustomerRepository.findById(validId)).thenReturn(Optional.of(customerEntity));

        // Act
        GetCustomerByIdResponse response = getCustomerByIdUseCase.getCustomerById(validId);

        // Assert
        assertNotNull(response);
        assertEquals(customer, response.getCustomer());
        verify(mockCustomerRepository).findById(validId);
        verify(mockCustomerRepository).existsById(validId);
    }

    @Test
    public void testCreateCustomerWithValidRequest() {
        // Arrange
        CreateCustomerRequest validRequest = new CreateCustomerRequest("John Doe", "johndoe@example.com", "123-456-7890");
        CustomerEntity savedCustomer = CustomerEntity.builder()
                .id(1L)
                .name("John Doe")
                .email("johndoe@example.com")
                .phoneNumber("123-456-7890")
                .build();
        when(mockCustomerRepository.save(any(CustomerEntity.class))).thenReturn(savedCustomer);

        // Act
        CreateCustomerResponse response = createCustomerUseCase.createCustomer(validRequest);

        // Assert
        assertNotNull(response);
        assertEquals(savedCustomer.getId(), response.getCustomerId());
        verify(mockCustomerRepository).save(any(CustomerEntity.class));
    }

//    @Test
//    public void testDeleteCustomerWithValidId() {
//        // Arrange
//        Long validId = 1L;
//        when(mockCustomerRepository.existsById(validId)).thenReturn(true);
//
//        // Act
//        DeleteCustomerResponse response = deleteCustomerUseCase.deleteCustomer(validId);
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(validId, response.getId());
//        verify(mockCustomerRepository).existsById(validId);
//        verify(mockCustomerRepository).deleteById(validId);
//    }

    @Test
    public void testDeleteCustomerWithInvalidId() {
        // Arrange
        Long invalidId = 99L;
        when(mockCustomerRepository.existsById(invalidId)).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidCustomerException.class, () -> deleteCustomerUseCase.deleteCustomer(invalidId));
        verify(mockCustomerRepository).existsById(invalidId);
        verify(mockCustomerRepository, never()).deleteById(invalidId);
    }

    @Test
    public void testUpdateCustomerWithValidRequest() {
        // Arrange
        long validCustomerId = 1L;
        UpdateCustomerRequest validRequest = new UpdateCustomerRequest(validCustomerId, "Jane Doe", "janedoe@example.com", "987-654-3210");
        CustomerEntity existingCustomer = CustomerEntity.builder()
                .id(validCustomerId)
                .name("John Doe")
                .email("johndoe@example.com")
                .phoneNumber("123-456-7890")
                .build();
        when(mockCustomerRepository.existsById(validCustomerId)).thenReturn(true);
        when(mockCustomerRepository.findById(validCustomerId)).thenReturn(Optional.of(existingCustomer));

        // Act
        UpdateCustomerResponse response = updateCustomerUseCase.updateCustomer(validRequest);

        // Assert
        assertNotNull(response);
        assertEquals(validCustomerId, response.getCustomerId());
        verify(mockCustomerRepository).existsById(validCustomerId);
        verify(mockCustomerRepository).findById(validCustomerId);
    }

    @Test
    public void testUpdateCustomerWithInvalidId() {
        // Arrange
        long invalidCustomerId = 99L;
        UpdateCustomerRequest requestWithInvalidId = new UpdateCustomerRequest(invalidCustomerId, "Fake Name", "fake@example.com", "000-000-0000");
        when(mockCustomerRepository.existsById(invalidCustomerId)).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidCustomerException.class, () -> updateCustomerUseCase.updateCustomer(requestWithInvalidId));
        verify(mockCustomerRepository).existsById(invalidCustomerId);
        verify(mockCustomerRepository, never()).findById(invalidCustomerId);
    }

//    @Test
//    public void testGetAllCustomersWithEmptyRepository() {
//        // Arrange
//        when(mockCustomerRepository.findAll()).thenReturn(Collections.emptyList());
//
//        // Act
//        GetAllCustomersResponse response = getAllCustomersUseCase.getCustomers();
//
//        // Assert
//        assertNotNull(response);
//        assertTrue(response.getCustomers().isEmpty());
//    }

//    @Test
//    public void testGetAllCustomersWithFilledRepository() {
//        // Arrange
//        List<CustomerEntity> customerEntities = List.of(
//                CustomerEntity.builder().id(1L).name("John").email("john@example.com").phoneNumber("123-456-7890").build(),
//                CustomerEntity.builder().id(2L).name("Jane").email("jane@example.com").phoneNumber("987-654-3210").build()
//        );
//        when(mockCustomerRepository.findAll()).thenReturn(customerEntities);
//
//        // Act
//        GetAllCustomersResponse response = getAllCustomersUseCase.getCustomers();
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(2, response.getCustomers().size());
//    }

    @Test
    public void testGetCustomerByIdWithNonExistingCustomer() {
        // Arrange
        Long nonExistentId = 999L;
        when(mockCustomerRepository.existsById(nonExistentId)).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidCustomerException.class, () -> getCustomerByIdUseCase.getCustomerById(nonExistentId));
        verify(mockCustomerRepository, never()).findById(nonExistentId);
        verify(mockCustomerRepository).existsById(nonExistentId);
    }

    @Test
    public void testGetCustomerByIdRepositoryError() {
        // Arrange
        Long validId = 1L;
        when(mockCustomerRepository.existsById(validId)).thenReturn(true);
        when(mockCustomerRepository.findById(validId)).thenThrow(new RuntimeException("Database Error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> getCustomerByIdUseCase.getCustomerById(validId));
        verify(mockCustomerRepository).findById(validId);
        verify(mockCustomerRepository).existsById(validId);
    }
}
