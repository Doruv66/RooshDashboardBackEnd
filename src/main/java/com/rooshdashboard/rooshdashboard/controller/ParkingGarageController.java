package com.rooshdashboard.rooshdashboard.controller;

import com.rooshdashboard.rooshdashboard.business.IParkingGarage.*;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidParkingGarageExeption;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/parkinggarage")
@AllArgsConstructor
@CrossOrigin

public class ParkingGarageController {
    private final UpdateParkingGarageUseCase updateParkingGarageUseCase;
    private final CreateParkingGarageUseCase createParkingGarageUseCase;
    private final GetParkingGarageUseCase getParkingGarageUseCase;
    private final DeleteParkingGarageUseCase deleteParkingGarageUseCase;
    private final GetParkingGarageByIdUseCase getParkingGarageByIdUseCase;

    @GetMapping
    public ResponseEntity<GetParkingGarageResponse> getParkingGarage() {
        return ResponseEntity.ok(getParkingGarageUseCase.getParkingGarage());
    }
    @GetMapping("{id}")
    public ResponseEntity<ParkingGarage> getParkingGarage(@PathVariable(value = "id") final long id) {
        final Optional<ParkingGarage> garage = getParkingGarageByIdUseCase.getParkingGarageById((id));
        if (garage.isEmpty())
        {
            throw new InvalidParkingGarageExeption("No garage found");
        }
        else
        {
            return ResponseEntity.ok().body(garage.get());
        }

    }
    @PostMapping()
    public ResponseEntity<CreateParkingGarageResponse> createParkingGarage(@RequestBody @Valid CreateParkingGarageRequest request) {
        CreateParkingGarageResponse response = createParkingGarageUseCase.CreateParkingGarage(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("{id}")
    public ResponseEntity<UpdateParkingGarageResponse> updateParkingGarage(@PathVariable("id") long id, @RequestBody @Valid UpdateParkingGarageRequest request){
        request.setId(id);
        UpdateParkingGarageResponse response = updateParkingGarageUseCase.updateParkingGarage(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<DeleteParkingGarageResponse> deleteParkingGarage(@PathVariable long id) {
        DeleteParkingGarageResponse response = deleteParkingGarageUseCase.deleteParkingGarage(id);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
