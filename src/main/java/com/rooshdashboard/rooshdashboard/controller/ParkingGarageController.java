package com.rooshdashboard.rooshdashboard.controller;

import com.rooshdashboard.rooshdashboard.business.IParkingGarage.*;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidParkingGarageExeption;
import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.*;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/parkinggarage")
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
public class ParkingGarageController {
    private final UpdateParkingGarageUseCase updateParkingGarageUseCase;
    private final CreateParkingGarageUseCase createParkingGarageUseCase;
    private final GetParkingGarageUseCase getParkingGarageUseCase;
    private final DeleteParkingGarageUseCase deleteParkingGarageUseCase;
    private final GetParkingGarageByIdUseCase getParkingGarageByIdUseCase;
    private final GetParkingGaragesByUserIdUseCase getParkingGaragesByUserIdUseCase;


    @GetMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<GetParkingGarageResponse> getParkingGarage() {
        return ResponseEntity.ok(getParkingGarageUseCase.getParkingGarage());
    }
    @GetMapping("{id}")
    @RolesAllowed({"ADMIN"})
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

    @GetMapping("/user/{userId}")
    @RolesAllowed({"USER", "ADMIN"}) // Adjust the roles as per your requirements
    public ResponseEntity<GetParkingGaragesByUserIdResponse> getParkingGaragesByUserId(@PathVariable(value = "userId") Long userId) {
        GetParkingGaragesByUserIdResponse response = getParkingGaragesByUserIdUseCase.getParkingGaragesByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<CreateParkingGarageResponse> createParkingGarage(
            @ModelAttribute CreateParkingGarageRequest request) {
        CreateParkingGarageResponse response = createParkingGarageUseCase.CreateParkingGarage(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping(value = "{id}", consumes = {"multipart/form-data"})
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<UpdateParkingGarageResponse> updateParkingGarage(
            @PathVariable("id") long id,
            @ModelAttribute UpdateParkingGarageRequest request) {

        request.setId(id);
        UpdateParkingGarageResponse response = updateParkingGarageUseCase.updateParkingGarage(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @DeleteMapping("{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<DeleteParkingGarageResponse> deleteParkingGarage(@PathVariable long id) {
        DeleteParkingGarageResponse response = deleteParkingGarageUseCase.deleteParkingGarage(id);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
