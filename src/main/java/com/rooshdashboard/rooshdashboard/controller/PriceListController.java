package com.rooshdashboard.rooshdashboard.controller;

import com.rooshdashboard.rooshdashboard.business.CreatePriceListUseCase;
import com.rooshdashboard.rooshdashboard.business.GetPriceListUseCase;
import com.rooshdashboard.rooshdashboard.business.UpdatePriceListUseCase;
import com.rooshdashboard.rooshdashboard.domain.PriceList.*;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pricelists")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PriceListController {
    private final GetPriceListUseCase getPriceListUseCase;
    private final CreatePriceListUseCase createPriceListUseCase;
    private final UpdatePriceListUseCase updatePriceListUseCase;


    @GetMapping("/byParkingGarage/{garage}")
    public ResponseEntity<GetPriceListResponse> getPriceListByParkingGarage(@PathVariable(value = "garage") ParkingGarageEntity garage) {
        return ResponseEntity.ok(getPriceListUseCase.getPriceListByParkingGarage(garage));
    }

    @PostMapping()
    public ResponseEntity<CreatePriceListResponse> createPriceList(@RequestBody @Valid CreatePriceListRequest request) {
        CreatePriceListResponse response = createPriceListUseCase.createPriceList(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdatePriceListResponse> updatePriceList(@RequestBody @Valid UpdatePriceListRequest request) {
        UpdatePriceListResponse response = updatePriceListUseCase.updatePriceList(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
