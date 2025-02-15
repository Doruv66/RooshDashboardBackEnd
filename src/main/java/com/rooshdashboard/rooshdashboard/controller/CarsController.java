package com.rooshdashboard.rooshdashboard.controller;

import com.rooshdashboard.rooshdashboard.business.*;
import com.rooshdashboard.rooshdashboard.domain.car.*;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
public class CarsController {
    private final GetAllCarsUseCase getCarsUseCase;
    private final GetCarByIdUseCase getCarUseCase;
    private final DeleteCarUseCase deleteCarUseCase;
    private final CreateCarUseCase createCarUseCase;
    private final UpdateCarUseCase updateCarUseCase;

    @GetMapping

    public ResponseEntity<GetAllCarsResponse> getCars() {
        return ResponseEntity.ok(getCarsUseCase.getCars());
    }
    @GetMapping("{carId}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<GetCarByIdResponse> getCar(@PathVariable(value = "carId") final long carId) {
        final GetCarByIdResponse car = getCarUseCase.getCar(carId);
        return ResponseEntity.ok().body(car);
    }
    @DeleteMapping("{carId}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<DeleteCarResponse> deleteCar(@PathVariable int carId) {
        return ResponseEntity.ok().body(deleteCarUseCase.deleteCar(carId));
    }
    @PostMapping()
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<CreateCarResponse> createCar(@RequestBody @Valid CreateCarRequest request) {
        CreateCarResponse response = createCarUseCase.createCar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<UpdateCarResponse> updateCar(@PathVariable("id") long id,
                                                       @RequestBody @Valid UpdateCarRequest request) {
        request.setId(id);
        return ResponseEntity.ok().body(updateCarUseCase.updateCar(request));
    }
}

