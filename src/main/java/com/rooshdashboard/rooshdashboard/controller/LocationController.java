package com.rooshdashboard.rooshdashboard.controller;

import com.rooshdashboard.rooshdashboard.business.location.*;
import com.rooshdashboard.rooshdashboard.domain.location.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/location")
@AllArgsConstructor
@CrossOrigin
public class LocationController {
    private final GetLocationByIdUseCase getLocationByIdUseCase;
    private final GetAllLocationsUseCase getAllLocationsUseCase;
    private final DeleteLocationUseCase deleteLocationUseCase;
    private final CreateLocationUseCase createLocationUseCase;
    private final UpdateLocationUseCase updateLocationUseCase;

    @GetMapping("{id}")
    public ResponseEntity<Location> getLocation(@PathVariable(value = "id") final long id){
        final Optional<Location> locationOptional = getLocationByIdUseCase.getLocation(id);
        if(locationOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(locationOptional.get());
    }

    @GetMapping
    public ResponseEntity<GetAllLocationsResponse> getAllLocations(){
        return ResponseEntity.ok(getAllLocationsUseCase.getLocations());
    }

    @DeleteMapping("{locationId}")
    public ResponseEntity<Void> deleteLocation(@PathVariable int locationId){
        deleteLocationUseCase.deleteLocation(locationId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<CreateLocationResponse> createLocation(@RequestBody @Valid CreateLocationRequest request){
        CreateLocationResponse response = createLocationUseCase.createLocation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<Void> updateLocation(@RequestBody @Valid UpdateLocationRequest request){
        updateLocationUseCase.updateLocation(request);
        return ResponseEntity.noContent().build();
    }













}
