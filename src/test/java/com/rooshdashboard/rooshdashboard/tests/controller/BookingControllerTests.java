package com.rooshdashboard.rooshdashboard.tests.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rooshdashboard.rooshdashboard.business.*;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidBookingException;
import com.rooshdashboard.rooshdashboard.controller.BookingController;
import com.rooshdashboard.rooshdashboard.domain.booking.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private GetAllBookingsUseCase getAllBookingsUseCase;
    @MockBean
    private GetBookingByIdUseCase getBookingByIdUseCase;
    @MockBean
    private DeleteBookingUseCase deleteBookingUseCase;
    @MockBean
    private UpdateBookingUseCase updateBookingUseCase;
    @MockBean
    private CreateBookingUseCase createBookingUseCase;

    Random random = new Random();
    LocalDateTime now = LocalDateTime.now();

    private List<Booking> generateFakeBookingList(long amountOfBookings) {
        List<Booking> bookings = new ArrayList<>();
        for (int i = 0; i < amountOfBookings; i++) {
            bookings.add(generateFakeBooking(i + 1));
        }
        return bookings;
    }

    private Booking generateFakeBooking(long id) {
        return Booking.builder()
                .id(id)
                .customerId((long) random.nextInt(1000) + 1)
                .carId((long) random.nextInt(1000) + 1)
                .startDate(now.minusDays(random.nextInt(10)))
                .endDate(now.plusDays(random.nextInt(10)))
                .flightNumberDeparture((long) random.nextInt(10000) + 1)
                .flightNumberArrival((long) random.nextInt(10000) + 1)
                .locationId((long) random.nextInt(100) + 1)
                .serviceId((long) random.nextInt(100) + 1)
                .build();
    }

    @Test
    void testGetAllBookings_ShouldReturn200ResponseWithBookingsArray() throws Exception {
        GetAllBookingResponse response = GetAllBookingResponse.builder().bookings(generateFakeBookingList(3L)).build();
        when(getAllBookingsUseCase.getAllBookings()).thenReturn(response);
        String expectedJson = objectMapper.writeValueAsString(response);
        mockMvc.perform(get("/bookings"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectedJson));
        verify(getAllBookingsUseCase).getAllBookings();
    }

    @Test
    void testGetBookingById_ShouldReturn200ResponseWithBooking() throws Exception {
        Booking booking = generateFakeBooking(1L);
        GetBookingByIdResponse response = GetBookingByIdResponse.builder().build();
        when(getBookingByIdUseCase.getBookingById(booking.getId())).thenReturn(response);
        String expectedJson = objectMapper.writeValueAsString(response);
        mockMvc.perform(get("/bookings/" + booking.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectedJson));
        verify(getBookingByIdUseCase).getBookingById(booking.getId());
    }

    @Test
    void testCreateBooking_ShouldReturn201ResponseWithCreatedBooking() throws Exception {
        CreateBookingRequest request = CreateBookingRequest.builder()
                .customerId((long) random.nextInt(1000) + 1)
                .carId((long) random.nextInt(1000) + 1)
                .startDate(now.minusDays(random.nextInt(10)))
                .endDate(now.plusDays(random.nextInt(10)))
                .flightNumberDeparture((long) random.nextInt(10000) + 1)
                .flightNumberArrival((long) random.nextInt(10000) + 1)
                .locationId((long) random.nextInt(100) + 1)
                .serviceId((long) random.nextInt(100) + 1)
                .build();
        CreateBookingResponse response = CreateBookingResponse.builder().id(request.getId()).build();
        when(createBookingUseCase.createBooking(request)).thenReturn(response);
        String expectedJson = objectMapper.writeValueAsString(response);
        mockMvc.perform(post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectedJson));
        verify(createBookingUseCase).createBooking(request);
    }

    @Test
    void testUpdateBooking_ShouldReturn200ResponseWithUpdatedBooking() throws Exception {
        Booking booking = generateFakeBooking(1L);
        UpdateBookingRequest request = UpdateBookingRequest.builder()
                .id(booking.getId())
                .carId(booking.getCarId())
                .customerId(booking.getCustomerId())
                .serviceId(booking.getServiceId())
                .locationId(booking.getLocationId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .flightNumberArrival(booking.getFlightNumberArrival())
                .flightNumberDeparture(booking.getFlightNumberDeparture())
                .build();
        UpdateBookingResponse response = UpdateBookingResponse.builder().id(booking.getId()).build();
        when(updateBookingUseCase.updateBooking(request)).thenReturn(response);
        String expectedJson = objectMapper.writeValueAsString(response);
        mockMvc.perform(put("/bookings/" + booking.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectedJson));
        verify(updateBookingUseCase).updateBooking(request);
    }

    @Test
    void testDeleteBooking_ShouldReturn200() throws Exception {
        Booking booking = generateFakeBooking(1L);
        mockMvc.perform(delete("/bookings/" + booking.getId()))
                .andDo(print())
                .andExpect(status().isOk());
        verify(deleteBookingUseCase).deleteBooking(booking.getId());
    }

    @Test
    void testGetBookingById_WithNonExistingId_ShouldReturn400() throws Exception {
        Long nonExistingId = 9999L;

        InvalidBookingException response = new InvalidBookingException("BOOKING_COULD_NOT_BE_FOUND");

        when(getBookingByIdUseCase.getBookingById(nonExistingId)).thenThrow(response);
        mockMvc.perform(get("/bookings/" + nonExistingId))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(getBookingByIdUseCase).getBookingById(nonExistingId);
    }

    @Test
    void testCreateBooking_WithInvalidData_ShouldReturn400() throws Exception {
        CreateBookingRequest request = CreateBookingRequest.builder()
                .customerId(null)
                .build();
        mockMvc.perform(post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateBooking_WithNonExistingId_ShouldReturn400() throws Exception {
        Booking booking = generateFakeBooking(9999L);
        UpdateBookingRequest request = UpdateBookingRequest.builder()
                .id(booking.getId())
                .carId(booking.getCarId())
                .build();
        when(updateBookingUseCase.updateBooking(request)).thenReturn(null);
        mockMvc.perform(put("/bookings/" + booking.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(updateBookingUseCase, never()).updateBooking(request);
    }

    @Test
    void testDeleteBooking_WithNonExistingId_ShouldReturn400() throws Exception {
        Long nonExistingId = 9999L;

        InvalidBookingException response = new InvalidBookingException("BOOKING_COULD_NOT_BE_FOUND");

        when(deleteBookingUseCase.deleteBooking(nonExistingId)).thenThrow(response);
        mockMvc.perform(delete("/bookings/" + nonExistingId))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(deleteBookingUseCase).deleteBooking(nonExistingId);
    }
}
