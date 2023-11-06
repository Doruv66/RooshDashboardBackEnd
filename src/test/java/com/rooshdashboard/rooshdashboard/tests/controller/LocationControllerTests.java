package com.rooshdashboard.rooshdashboard.tests.controller;

import com.rooshdashboard.rooshdashboard.business.exception.InvalidLocationException;
import com.rooshdashboard.rooshdashboard.domain.location.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LocationController.class)
public class LocationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GetLocationByIdUseCase getLocationByIdUseCase;

    @MockBean
    private GetAllLocationsUseCase getAllLocationsUseCase;

    @MockBean
    private DeleteLocationUseCase deleteLocationUseCase;

    @MockBean
    private CreateLocationUseCase createLocationUseCase;

    @MockBean
    private UpdateLocationUseCase updateLocationUseCase;

    private Location generateFakeLocation(long id) {
        return Location.builder()
                .id(id)
                .floor((int) id)
                .parkingSlot((int) id)
                .build();
    }


    @Test
    void testGetAllLocations_ShouldReturn200ResponseWithLocationsArray() throws Exception {
        List<Location> locations = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            locations.add(generateFakeLocation(i + 1));
        }
        GetAllLocationsResponse response = new GetAllLocationsResponse(locations);
        when(getAllLocationsUseCase.getLocations()).thenReturn(response);
        String expectedJson = objectMapper.writeValueAsString(response);
        mockMvc.perform(get("/location"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
        verify(getAllLocationsUseCase).getLocations();
    }

    @Test
    void testGetAllLocations_ShouldReturnEmptyListWhenNoLocations() throws Exception {
        GetAllLocationsResponse response = new GetAllLocationsResponse(new ArrayList<>());
        when(getAllLocationsUseCase.getLocations()).thenReturn(response);
        String expectedJson = objectMapper.writeValueAsString(response);
        mockMvc.perform(get("/location"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
        verify(getAllLocationsUseCase).getLocations();
    }

    @Test
    void testGetLocationById_ShouldReturn200ResponseWithLocation() throws Exception {
        Location location = generateFakeLocation(1L);
        when(getLocationByIdUseCase.getLocation(location.getId())).thenReturn(Optional.of(location));
        String expectedJson = objectMapper.writeValueAsString(location);
        mockMvc.perform(get("/location/" + location.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
        verify(getLocationByIdUseCase).getLocation(location.getId());
    }

    @Test
    void testGetLocationById_ShouldReturn404ResponseWhenLocationNotFound() throws Exception {
        long locationId = 1L;
        when(getLocationByIdUseCase.getLocation(locationId)).thenReturn(Optional.empty());
        mockMvc.perform(get("/location/" + locationId))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(getLocationByIdUseCase).getLocation(locationId);
    }

    @Test
    void testDeleteLocation_ShouldReturn204() throws Exception {
        long locationId = 1L;
        doNothing().when(deleteLocationUseCase).deleteLocation(locationId);
        mockMvc.perform(delete("/location/" + locationId))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(deleteLocationUseCase).deleteLocation(locationId);
    }

    @Test
    void testDeleteLocation_ShouldReturn400() throws Exception {
        long locationId = 1L;
        doThrow(new InvalidLocationException("LOCATION_NOT_FOUND")).when(deleteLocationUseCase).deleteLocation(locationId);
        mockMvc.perform(delete("/location/" + locationId))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(deleteLocationUseCase).deleteLocation(locationId);
    }

//    @Test
//    void testCreateLocation_ShouldReturn201ResponseWithLocation() throws Exception {
//        Long validId = 1L;
//        CreateLocationRequest request = CreateLocationRequest.builder()
//                .locationId(null)
//                .floor(1)
//                .parkingSlot(100)
//                .build();
//        CreateLocationResponse response = CreateLocationResponse.builder()
//                .locationId(validId).build();
//        when(createLocationUseCase.createLocation(request)).thenReturn(response);
//        String expectedJson = objectMapper.writeValueAsString(response);
//        mockMvc.perform(post("/location")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(content().json(expectedJson));
//        verify(createLocationUseCase).createLocation(request);
//    }

    @Test
    void testCreateLocation_WithInvalidData_ShouldReturn400() throws Exception {
        CreateLocationRequest request = new CreateLocationRequest();
        doThrow(new InvalidLocationException("INVALID_LOCATION_DATA")).when(createLocationUseCase).createLocation(request);
        mockMvc.perform(post("/location")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(createLocationUseCase, never()).createLocation(request);
    }

    @Test
    void testUpdateLocation_ShouldReturn400() throws Exception {
        UpdateLocationRequest request = new UpdateLocationRequest();
        doNothing().when(updateLocationUseCase).updateLocation(request);
        mockMvc.perform(put("/location")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(updateLocationUseCase, never()).updateLocation(request);
    }

    @Test
    void testUpdateLocation_WithInvalidData_ShouldReturn400() throws Exception {
        UpdateLocationRequest request = new UpdateLocationRequest();
        doThrow(new InvalidLocationException("INVALID_LOCATION_DATA")).when(updateLocationUseCase).updateLocation(request);
        mockMvc.perform(put("/location")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(updateLocationUseCase, never()).updateLocation(request);
    }
}

