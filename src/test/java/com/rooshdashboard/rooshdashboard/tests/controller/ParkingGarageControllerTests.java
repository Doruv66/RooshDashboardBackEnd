package com.rooshdashboard.rooshdashboard.tests.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rooshdashboard.rooshdashboard.business.IParkingGarage.*;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidParkingGarageExeption;
import com.rooshdashboard.rooshdashboard.business.impl.ParkingGarage.ParkingGarageConverter;
import com.rooshdashboard.rooshdashboard.controller.ParkingGarageController;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.*;

import com.rooshdashboard.rooshdashboard.domain.booking.Booking;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ParkingGarageController.class)
public class ParkingGarageControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UpdateParkingGarageUseCase updateParkingGarageUseCase;

    @MockBean
    private CreateParkingGarageUseCase createParkingGarageUseCase;

    @MockBean
    private GetParkingGarageUseCase getParkingGarageUseCase;

    @MockBean
    private DeleteParkingGarageUseCase deleteParkingGarageUseCase;

    @MockBean
    private GetParkingGarageByIdUseCase getParkingGarageByIdUseCase;


    @Test
    void testGetParkingGarage_ShouldReturn200ResponseWithParkingGarages() throws Exception {
        List<ParkingGarage> parkingGarageList = new ArrayList<>();

        for (int i = 0; i < 3; i++){
            ParkingGarageEntity parkingGarageEntity = ParkingGarageEntity.builder()
                    .id((long) i+1)
                    .location("123 Main St")
                    .build();
            parkingGarageList.add(ParkingGarageConverter.convert(parkingGarageEntity));
        }

        GetParkingGarageResponse response = GetParkingGarageResponse.builder().parkingGarages(parkingGarageList).build();
        when(getParkingGarageUseCase.getParkingGarage()).thenReturn(response);
        String expectedJson = objectMapper.writeValueAsString(response);


        mockMvc.perform(get("/parkinggarage"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

        verify(getParkingGarageUseCase).getParkingGarage();
    }

    @Test
    void testGetParkingGarageById_ShouldReturn200ResponseWithParkingGarage() throws Exception {
        long garageId = 1L;
        ParkingGarage garage = ParkingGarage.builder()
                .id(garageId)
                .location("123 Main St")
                .build();

        when(getParkingGarageByIdUseCase.getParkingGarageById(garageId)).thenReturn(Optional.of(garage));
        String expectedJson = objectMapper.writeValueAsString(garage);


        mockMvc.perform(get("/parkinggarage/" + garageId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

        verify(getParkingGarageByIdUseCase).getParkingGarageById(garageId);
    }

    @Test
    void testGetParkingGarageById_ShouldReturn400ResponseWhenGarageNotFound() throws Exception {
        long garageId = 1L;
        when(getParkingGarageByIdUseCase.getParkingGarageById(garageId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/parkinggarage/" + garageId))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidParkingGarageExeption));

        verify(getParkingGarageByIdUseCase).getParkingGarageById(garageId);
    }

    //under
//    @Test
//    void testCreateParkingGarage_ShouldReturn201ResponseWithCreatedGarage() throws Exception {
//        CreateParkingGarageRequest request = CreateParkingGarageRequest.builder()
//                .id(null)
//                .Address("123 Main St")
//                .bookingId(456)
//                .build();
//        CreateParkingGarageResponse response = CreateParkingGarageResponse.builder()
//                .id(1L)
//                .build();
//        when(createParkingGarageUseCase.CreateParkingGarage(request)).thenReturn(response);
//        String expectedJson = objectMapper.writeValueAsString(response);
//
//        mockMvc.perform(post("/parkinggarage")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(content().json(expectedJson));
//
//        verify(createParkingGarageUseCase).CreateParkingGarage(request);
//    }

//    @Test
//    void testUpdateParkingGarage_ShouldReturn201ResponseWithUpdatedGarage() throws Exception {
//        long garageId = 1L;
//        UpdateParkingGarageRequest request = UpdateParkingGarageRequest.builder()
//                .id(garageId)
//                .location("123 Main St")
//                .bookingId(456)
//                .build();
//        UpdateParkingGarageResponse response = UpdateParkingGarageResponse.builder().message("").build();
//        when(updateParkingGarageUseCase.updateParkingGarage(request)).thenReturn(response);
//        String expectedJson = objectMapper.writeValueAsString(response);
//
//        mockMvc.perform(put("/parkinggarage/" + garageId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(content().json(expectedJson));
//
//        verify(updateParkingGarageUseCase).updateParkingGarage(request);
//    }

    @Test
    void testDeleteParkingGarage_ShouldReturn201ResponseWithDeleteResponse() throws Exception {
        long garageId = 1L;
        DeleteParkingGarageResponse response = DeleteParkingGarageResponse.builder().message("").build();
        when(deleteParkingGarageUseCase.deleteParkingGarage(garageId)).thenReturn(response);
        String expectedJson = objectMapper.writeValueAsString(response);

        mockMvc.perform(delete("/parkinggarage/" + garageId))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));

        verify(deleteParkingGarageUseCase).deleteParkingGarage(garageId);
    }

    @Test
    void testCreateParkingGarage_ShouldReturn400ResponseForBadInput() throws Exception {
        CreateParkingGarageRequest badRequest = null;


        mockMvc.perform(post("/parkinggarage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(badRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(createParkingGarageUseCase, never()).CreateParkingGarage(any());
    }


    // Sad Path: Update a non-existing parking garage
    @Test
    void testUpdateParkingGarage_ShouldReturn400WhenGarageNotFound() throws Exception {
        long garageId = 999L;
        UpdateParkingGarageRequest request = UpdateParkingGarageRequest.builder().id(garageId).build();
        when(updateParkingGarageUseCase.updateParkingGarage(request)).thenThrow(new InvalidParkingGarageExeption("No garage found for the provided ID"));

        mockMvc.perform(put("/parkinggarage/" + garageId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(updateParkingGarageUseCase, never()).updateParkingGarage(request);
    }

    @Test
    void testDeleteParkingGarage_ShouldReturn404WhenGarageNotFound() throws Exception {
        long garageId = 999L;
        when(deleteParkingGarageUseCase.deleteParkingGarage(garageId)).thenThrow(new InvalidParkingGarageExeption("No garage found for the provided ID"));

        mockMvc.perform(delete("/parkinggarage/" + garageId))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(deleteParkingGarageUseCase).deleteParkingGarage(garageId);
    }

}
