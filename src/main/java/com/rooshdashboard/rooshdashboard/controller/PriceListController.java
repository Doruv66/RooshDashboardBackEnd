package com.rooshdashboard.rooshdashboard.controller;

import com.rooshdashboard.rooshdashboard.business.CreatePriceListUseCase;
import com.rooshdashboard.rooshdashboard.business.DeletePriceListUseCase;
import com.rooshdashboard.rooshdashboard.business.GetPriceListUseCase;
import com.rooshdashboard.rooshdashboard.business.UpdatePriceListUseCase;
import com.rooshdashboard.rooshdashboard.domain.Customer.DeleteCustomerResponse;
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
    private final DeletePriceListUseCase deletePriceListUseCase;
    @RolesAllowed({"ADMIN"})
    @GetMapping("/byParkingGarage/{garage}")
    public ResponseEntity<GetPriceListResponse> getPriceListByParkingGarage(@PathVariable(value = "garage") ParkingGarageEntity garage) {
        return ResponseEntity.ok(getPriceListUseCase.getPriceListByParkingGarage(garage));
    }
    @RolesAllowed({"ADMIN"})
    @GetMapping("/byStartDateEndDate")
    public ResponseEntity<GetPriceListResponse> getPriceListByStartDateEndDate(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {

        GetPriceListResponse response = getPriceListUseCase.getPriceListByStartDateEndDate(startDate, endDate);
        return ResponseEntity.ok().body(response);
    }


    @RolesAllowed({"ADMIN"})
    @PostMapping()
    public ResponseEntity<CreatePriceListResponse> createPriceList(@RequestBody @Valid CreatePriceListRequest request) {
        CreatePriceListResponse response = createPriceListUseCase.createPriceList(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @RolesAllowed({"ADMIN"})
    @PutMapping("{id}")
    public ResponseEntity<UpdatePriceListResponse> updatePriceList(@RequestBody @Valid UpdatePriceListRequest request) {
        UpdatePriceListResponse response = updatePriceListUseCase.updatePriceList(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<DeletePriceListResponse> deletePriceList(
            @RequestParam(required = true) String startDate,
            @RequestParam(required = true) String endDate) {
        final DeletePriceListResponse response = deletePriceListUseCase.deletePriceList(startDate, endDate);
        return ResponseEntity.ok().body(response);
    }
}
