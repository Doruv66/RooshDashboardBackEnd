package com.rooshdashboard.rooshdashboard.controller;

import com.rooshdashboard.rooshdashboard.business.*;
import com.rooshdashboard.rooshdashboard.domain.service.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
@AllArgsConstructor
@CrossOrigin("http://localhost;5173/")
public class ServiceController {
    private final GetAllServicesUseCase getAllServicesUseCase;
    private final GetServiceByIdUseCase getServiceByIdUseCase;
    private final DeleteServiceUseCase deleteServiceUseCase;
    private final UpdateServiceUseCase updateServiceUseCase;
    private final CreateServiceUseCase createServiceUseCase;

    @GetMapping
    public ResponseEntity<GetAllServicesResponse> getServices(){
        return ResponseEntity.ok(getAllServicesUseCase.getAllServices());
    }

    @GetMapping("{serviceId}")
    public ResponseEntity<Service> getService(@PathVariable(value = "serviceId") final long serviceId) {
        final Service service = getServiceByIdUseCase.getServiceById(serviceId);
        return ResponseEntity.ok().body(service);
    }

    @DeleteMapping("{serviceId}")
    public ResponseEntity<DeleteServiceResponse> deleteService(@PathVariable(value = "serviceId") final long serviceId) {
        return ResponseEntity.ok().body(deleteServiceUseCase.deleteService(serviceId));
    }

    @PostMapping ResponseEntity<CreateServiceResponse> createService(@RequestBody @Valid CreateServiceRequest request) {
        CreateServiceResponse response = createServiceUseCase.createService(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateServiceResponse> updateService(@PathVariable("id") long id,
                                                               @RequestBody @Valid UpdateServiceRequest request) {
        return ResponseEntity.ok().body(updateServiceUseCase.updateService(request));
    }
}
