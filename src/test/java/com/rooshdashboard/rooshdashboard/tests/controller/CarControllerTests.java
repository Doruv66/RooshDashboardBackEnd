package com.rooshdashboard.rooshdashboard.tests.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rooshdashboard.rooshdashboard.business.*;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidCarException;
import com.rooshdashboard.rooshdashboard.controller.CarsController;
import com.rooshdashboard.rooshdashboard.domain.car.*;
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
import java.util.Random;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarsController.class)
public class CarControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private GetAllCarsUseCase getCarsUseCase;
    @MockBean
    private GetCarByIdUseCase getCarUseCase;
    @MockBean
    private DeleteCarUseCase deleteCarUseCase;
    @MockBean
    private UpdateCarUseCase updateCarUseCase;
    @MockBean
    private CreateCarUseCase createCarUseCase;

    Random random = new Random();

    private Car generateFakeCar(long id) {
        return Car.builder()
                .id(id)
                .brand("CarMake" + id)
                .model("CarModel" + id)
                .electric(true)
                .licensePlate("fakeplate")
                .build();
    }

    @Test
    void testGetCars_ShouldReturn200ResponseWithCarsArray() throws Exception {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            cars.add(generateFakeCar(i+1));
        }
        GetAllCarsResponse response = GetAllCarsResponse.builder().cars(cars).build();
        when(getCarsUseCase.getCars()).thenReturn(response);
        String expectedJson = objectMapper.writeValueAsString(response);
        mockMvc.perform(get("/cars"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectedJson));
        verify(getCarsUseCase).getCars();
    }

    @Test
    void testGetCarById_ShouldReturn200ResponseWithCar() throws Exception {
        Car car = generateFakeCar(1L);
        GetCarByIdResponse response = GetCarByIdResponse.builder().car(car).build();
        when(getCarUseCase.getCar(car.getId())).thenReturn(response);
        String expectedJson = objectMapper.writeValueAsString(response);
        mockMvc.perform(get("/cars/" + car.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectedJson));
        verify(getCarUseCase).getCar(car.getId());
    }

    @Test
    void testCreateCar_ShouldReturn201ResponseWithCreatedCar() throws Exception {
        long id = 1L;
        CreateCarRequest request = CreateCarRequest.builder()
                .brand("CarMake" + id)
                .model("CarModel" + id)
                .electric(true)
                .licensePlate("fakeplate")
                .build();
        CreateCarResponse response = CreateCarResponse.builder().id(1L).build();
        when(createCarUseCase.createCar(request)).thenReturn(response);
        String expectedJson = objectMapper.writeValueAsString(response);
        mockMvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectedJson));
        verify(createCarUseCase).createCar(request);
    }

    @Test
    void testUpdateCar_ShouldReturn200ResponseWithUpdatedCar() throws Exception {
        long id = 1L;
        UpdateCarRequest request = UpdateCarRequest.builder()
                .id(id)
                .brand("CarMake" + id)
                .model("CarModel" + id)
                .electric(true)
                .licensePlate("fakeplate")
                .build();
        UpdateCarResponse response = UpdateCarResponse.builder().id(id).build(); // Adjust according to your UpdateCarResponse structure
        when(updateCarUseCase.updateCar(any(UpdateCarRequest.class))).thenReturn(response);
        String expectedJson = objectMapper.writeValueAsString(response);
        mockMvc.perform(put("/cars/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectedJson));
        verify(updateCarUseCase).updateCar(any(UpdateCarRequest.class));
    }

    @Test
    void testDeleteCar_ShouldReturn200Response() throws Exception {
        long carId = 1L;
        DeleteCarResponse response = DeleteCarResponse.builder().id(carId).build();
        when((deleteCarUseCase).deleteCar(carId)).thenReturn(response);
        mockMvc.perform(delete("/cars/" + carId))
                .andDo(print())
                .andExpect(status().isOk());
        verify(deleteCarUseCase).deleteCar(carId);
    }

    @Test
    void testGetCarById_ShouldReturn404ResponseWhenCarNotFound() throws Exception {
        long carId = 1L;
        when(getCarUseCase.getCar(carId)).thenThrow(new InvalidCarException("CAR_NOT_FOUND"));
        mockMvc.perform(get("/cars/" + carId))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(getCarUseCase).getCar(carId);
    }

    @Test
    void testCreateCar_WithInvalidData_ShouldReturn400() throws Exception {
        CreateCarRequest request = new CreateCarRequest();
        mockMvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(createCarUseCase, never()).createCar(any(CreateCarRequest.class));
    }

    @Test
    void testUpdateCar_ShouldReturn404WhenCarNotFound() throws Exception {
        long id = 999L;
        UpdateCarRequest request = UpdateCarRequest.builder()
                .id(id)
                .brand("CarMake" + id)
                .model("CarModel" + id)
                .electric(true)
                .licensePlate("fakeplate")
                .build();
        when(updateCarUseCase.updateCar(any(UpdateCarRequest.class))).thenThrow(new InvalidCarException("CAR_NOT_FOUND"));
        mockMvc.perform(put("/cars/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
        verify(updateCarUseCase).updateCar(any(UpdateCarRequest.class));
    }

}
