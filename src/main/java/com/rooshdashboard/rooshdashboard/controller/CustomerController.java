package com.rooshdashboard.rooshdashboard.controller;

import com.rooshdashboard.rooshdashboard.business.*;
import com.rooshdashboard.rooshdashboard.domain.Customer.*;
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
    public ResponseEntity<GetCustomersResponse> getCustomers() {
        return ResponseEntity.ok(getCustomersUseCase.getCustomers());
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") final long id) {
        final Customer customer = getCustomerByIdUseCase.getCustomerById(id);
        return ResponseEntity.ok().body(customer);
    }

    @DeleteMapping("{CustomerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long CustomerId) {
        deleteCustomerUseCase.deleteCustomer(CustomerId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<CreateCustomerResponse> createCustomer(@RequestBody @Valid CreateCustomerRequest request) {
        CreateCustomerResponse response = createCustomerUseCase.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Long> updateCustomer(@PathVariable("id") long id,
                                              @RequestBody @Valid UpdateCustomerRequest request) {
        request.setId(id);
        updateCustomerUseCase.updateCustomer(request);
        return ResponseEntity.ok().body(request.id);
    }

}
