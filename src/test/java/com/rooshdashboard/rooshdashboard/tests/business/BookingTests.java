package com.rooshdashboard.rooshdashboard.tests.business;

import com.rooshdashboard.rooshdashboard.business.exception.InvalidBookingException;
import com.rooshdashboard.rooshdashboard.business.impl.booking.*;
import com.rooshdashboard.rooshdashboard.domain.booking.*;
import com.rooshdashboard.rooshdashboard.persistance.BookingRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.BookingEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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
        BookingEntity bookingEntity = BookingEntity.builder().id(validId).build();
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

        CreateBookingRequest request = CreateBookingRequest.builder()
                .id(savedBooking.getId())
                .customerId((long) random.nextInt(1000) + 1)
                .carId((long) random.nextInt(1000) + 1)
                .startDate(now.minusDays(random.nextInt(10)))
                .endDate(now.plusDays(random.nextInt(10)))
                .flightNumberDeparture((long) random.nextInt(10000) + 1)
                .flightNumberArrival((long) random.nextInt(10000) + 1)
                .locationId((long) random.nextInt(100) + 1)
                .serviceId((long) random.nextInt(100) + 1)
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
        BookingEntity existingBooking = BookingEntity.builder().id(validBookingId).build();

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
        List<BookingEntity> bookingEntities = IntStream.range(1, 6)
                .mapToObj(i -> BookingEntity.builder().id((long) i).build())
                .collect(Collectors.toList());
        when(mockBookingRepository.findAll()).thenReturn(bookingEntities);

        // Act
        GetAllBookingResponse response = getAllBookingsUseCase.getAllBookings();

        // Assert
        assertNotNull(response);
        assertEquals(5, response.getBookings().size());

        verify(mockBookingRepository).findAll();
    }
}

