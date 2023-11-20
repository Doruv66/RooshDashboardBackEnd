package com.rooshdashboard.rooshdashboard.controller;

import com.rooshdashboard.rooshdashboard.business.*;
import com.rooshdashboard.rooshdashboard.domain.Customer.*;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Customers")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {
    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;
    private final GetCustomersUseCase getCustomersUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;

    @GetMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<GetCustomersResponse> getCustomers() {
        return ResponseEntity.ok(getCustomersUseCase.getCustomers());
    }

    @GetMapping("{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<GetCustomerByIdResponse> getCustomerById(@PathVariable(value = "id") final long id) {
        final GetCustomerByIdResponse response = getCustomerByIdUseCase.getCustomerById(id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("{CustomerId}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<DeleteCustomerResponse> deleteCustomer(@PathVariable long CustomerId) {
        final DeleteCustomerResponse response = deleteCustomerUseCase.deleteCustomer(CustomerId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping()
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<CreateCustomerResponse> createCustomer(@RequestBody @Valid CreateCustomerRequest request) {
        CreateCustomerResponse response = createCustomerUseCase.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<Long> updateCustomer(@PathVariable("id") long id,
                                              @RequestBody @Valid UpdateCustomerRequest request) {
        request.setId(id);
        updateCustomerUseCase.updateCustomer(request);
        return ResponseEntity.ok().body(request.id);
    }

}
