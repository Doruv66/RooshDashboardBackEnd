package com.rooshdashboard.rooshdashboard.tests.business;

import com.rooshdashboard.rooshdashboard.business.exception.InvalidBookingException;
import com.rooshdashboard.rooshdashboard.business.impl.booking.*;
import com.rooshdashboard.rooshdashboard.domain.booking.*;
import com.rooshdashboard.rooshdashboard.persistance.BookingRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingTests {

    @Mock
    private BookingRepository mockBookingRepository;

    @InjectMocks
    private CreateBookingUseCaseImpl createBookingUseCase;
    @InjectMocks
    private UpdateBookingUseCaseImpl updateBookingUseCase;
    @InjectMocks
    private DeleteBookingUseCaseImpl deleteBookingUseCase;
    @InjectMocks
    private GetAllBookingsUseCase getAllBookingsUseCase;
    @InjectMocks
    private GetBookingByIdUseCaseImpl getBookingByIdUseCase;

    Random random = new Random();
    LocalDateTime now = LocalDateTime.now();

    @Test
    public void testGetBookingByIdWithExistingBooking() {
        // Arrange
        Long validId = 1L;
        ParkingGarageUtilityEntity fakeUtility = new ParkingGarageUtilityEntity();

        UserEntity adminUser = UserEntity.builder()
                .username("admin@gmail.com")
                .password("123456789")
                .build();
        UserRoleEntity adminRole = UserRoleEntity.builder().role(RoleEnum.ADMIN).user(adminUser).build();
        adminUser.setUserRoles(Set.of(adminRole));

        ParkingGarageEntity fakeGarage = ParkingGarageEntity.builder().account(adminUser).parkingGarageUtility(fakeUtility).build();
        ServiceEntity fakeService = new ServiceEntity();
        CustomerEntity fakeCustomer = CustomerEntity.builder()

                .build();
        CarEntity fakeCar = CarEntity.builder().customer(fakeCustomer).build();
        BookingEntity bookingEntity = BookingEntity.builder()
                .id(validId)
                .garage(fakeGarage)
                .service(fakeService)
                .customer(fakeCustomer)
                .car(fakeCar)
                .build();
        Booking booking  = BookingConverters.convertToBooking(bookingEntity);

        when(mockBookingRepository.existsById(validId)).thenReturn(true);
        when(mockBookingRepository.findById(validId)).thenReturn(Optional.of(bookingEntity));

        // Act
        GetBookingByIdResponse response = getBookingByIdUseCase.getBookingById(validId);

        // Assert
        assertNotNull(response);
        assertEquals(booking, response.getBooking());

        verify(mockBookingRepository).findById(validId);
        verify(mockBookingRepository).existsById(validId);
    }

    @Test
    public void testGetBookingByIdWithInvalidBookingId() {
        // Arrange
        Long invalidId = 99L;
        when(mockBookingRepository.existsById(invalidId)).thenReturn(false);

        // Act and Assert
        assertThrows(InvalidBookingException.class, () -> getBookingByIdUseCase.getBookingById(invalidId));

        verify(mockBookingRepository).existsById(invalidId);
        verify(mockBookingRepository, never()).findById(invalidId);
    }

    @Test
    public void testCreateBookingWithValidRequest() {
        // Arrange
        BookingEntity savedBooking = BookingEntity.builder().id(1L).build();

        CustomerEntity customer = CustomerEntity.builder().build();

        CarEntity car = CarEntity.builder().build();

        ParkingGarageEntity parkingGarageEntity = ParkingGarageEntity.builder().build();

        ServiceEntity service = ServiceEntity.builder().build();

        CreateBookingRequest request = CreateBookingRequest.builder()
                .id(savedBooking.getId())
                .customer(customer)
                .car(car)
                .startDate(now.minusDays(random.nextInt(10)))
                .endDate(now.plusDays(random.nextInt(10)))
                .flightNumberDeparture((long) random.nextInt(10000) + 1)
                .flightNumberArrival((long) random.nextInt(10000) + 1)
                .garage(parkingGarageEntity)
                .service(service)
                .build();

        when(mockBookingRepository.save(any(BookingEntity.class))).thenReturn(savedBooking);

        // Act
        CreateBookingResponse response = createBookingUseCase.createBooking(request);

        // Assert
        assertNotNull(response);
        assertEquals(savedBooking.getId(), response.getId());
    }

    @Test
    public void testDeleteBookingWithValidId() {
        // Arrange
        Long validId = 1L;
        when(mockBookingRepository.existsById(validId)).thenReturn(true);

        // Act
        DeleteBookingResponse response = deleteBookingUseCase.deleteBooking(validId);

        // Assert
        assertNotNull(response);
        assertEquals(validId, response.getId());

        verify(mockBookingRepository).existsById(validId);
        verify(mockBookingRepository).deleteById(validId);
    }

    @Test
    public void testDeleteBookingWithInvalidId() {
        // Arrange
        Long invalidId = 99L;
        when(mockBookingRepository.existsById(invalidId)).thenReturn(false);

        // Act and Assert
        assertThrows(InvalidBookingException.class, () -> deleteBookingUseCase.deleteBooking(invalidId));

        verify(mockBookingRepository).existsById(invalidId);
        verify(mockBookingRepository, never()).deleteById(invalidId);
    }

    @Test
    public void testUpdateBookingWithValidRequest() {
        // Arrange
        long validBookingId = 1L;
        UpdateBookingRequest validRequest = UpdateBookingRequest.builder().id(validBookingId).build();
        ParkingGarageUtilityEntity fakeUtility = new ParkingGarageUtilityEntity();

        UserEntity adminUser = UserEntity.builder()
                .username("admin@gmail.com")
                .password("123456789")
                .build();
        UserRoleEntity adminRole = UserRoleEntity.builder().role(RoleEnum.ADMIN).user(adminUser).build();
        adminUser.setUserRoles(Set.of(adminRole));

        ParkingGarageEntity fakeGarage = ParkingGarageEntity.builder().account(adminUser).parkingGarageUtility(fakeUtility).build();
        ServiceEntity fakeService = new ServiceEntity();
        CustomerEntity fakeCustomer = new CustomerEntity();
        CarEntity fakeCar = CarEntity.builder().customer(fakeCustomer).build();
        BookingEntity existingBooking = BookingEntity.builder().id(validBookingId).garage(fakeGarage).service(fakeService).customer(fakeCustomer).car(fakeCar).build();

        when(mockBookingRepository.existsById(validBookingId)).thenReturn(true);
        when(mockBookingRepository.findById(validBookingId)).thenReturn(Optional.of(existingBooking));

        // Act
        UpdateBookingResponse response = updateBookingUseCase.updateBooking(validRequest);

        // Assert
        assertNotNull(response);
        assertEquals(validBookingId, response.getId());

        verify(mockBookingRepository).existsById(validBookingId);
        verify(mockBookingRepository).findById(validBookingId);
    }

    @Test
    public void testUpdateBookingWithInvalidBookingId() {
        // Arrange
        long invalidBookingId = 99L;
        UpdateBookingRequest requestWithInvalidId = UpdateBookingRequest.builder().id(invalidBookingId).build();
        when(mockBookingRepository.existsById(invalidBookingId)).thenReturn(false);

        // Act and Assert
        assertThrows(InvalidBookingException.class, () -> updateBookingUseCase.updateBooking(requestWithInvalidId));

        verify(mockBookingRepository).existsById(invalidBookingId);
        verify(mockBookingRepository, never()).findById(invalidBookingId);
    }

    @Test
    public void testGetAllBookingsWithEmptyRepository() {
        // Arrange
        when(mockBookingRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        GetAllBookingResponse response = getAllBookingsUseCase.getAllBookings();

        // Assert
        assertNotNull(response);
        assertTrue(response.getBookings().isEmpty());
    }

    @Test
    public void testGetAllBookingsWithFilledRepository() {
        // Arrange
        List<BookingEntity> storageList = new ArrayList<>();
        ParkingGarageUtilityEntity fakeUtility = new ParkingGarageUtilityEntity();

        UserEntity adminUser = UserEntity.builder()
                .username("admin@gmail.com")
                .password("123456789")
                .build();
        UserRoleEntity adminRole = UserRoleEntity.builder().role(RoleEnum.ADMIN).user(adminUser).build();
        adminUser.setUserRoles(Set.of(adminRole));

        ParkingGarageEntity fakeGarage = ParkingGarageEntity.builder().account(adminUser).parkingGarageUtility(fakeUtility).build();
        ServiceEntity fakeService = new ServiceEntity();
        CustomerEntity fakeCustomer = new CustomerEntity();
        CarEntity fakeCar = CarEntity.builder().customer(fakeCustomer).build();
        BookingEntity bookingEntity1 = BookingEntity.builder().id(1L).garage(fakeGarage).service(fakeService).customer(fakeCustomer).car(fakeCar).build();
        BookingEntity bookingEntity2 = BookingEntity.builder().id(1L).garage(fakeGarage).service(fakeService).customer(fakeCustomer).car(fakeCar).build();
        storageList.add(bookingEntity1);
        storageList.add(bookingEntity2);
        when(mockBookingRepository.findAll()).thenReturn(storageList);

        // Act
        GetAllBookingResponse response = getAllBookingsUseCase.getAllBookings();

        // Assert
        assertNotNull(response);
        assertEquals(2, response.getBookings().size());

        verify(mockBookingRepository).findAll();
    }
}

