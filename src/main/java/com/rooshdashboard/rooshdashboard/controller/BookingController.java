package com.rooshdashboard.rooshdashboard.controller;

import com.rooshdashboard.rooshdashboard.business.*;
import com.rooshdashboard.rooshdashboard.business.impl.booking.FilterBookingUseCaseImpl;
import com.rooshdashboard.rooshdashboard.domain.booking.*;
import com.rooshdashboard.rooshdashboard.domain.service.ServiceType;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
@CrossOrigin("http://localhost:5173/")
public class BookingController {
    private final GetAllBookingsUseCase getAllBookingsUseCase;
    private final GetBookingByIdUseCase getBookingByIdUseCase;
    private final DeleteBookingUseCase deleteBookingUseCase;
    private final UpdateBookingUseCase updateBookingUseCase;
    private final CreateBookingUseCase createBookingUseCase;
    private final FilterBookingsUseCase filterBookingsUseCase;
    @GetMapping
    public ResponseEntity<GetAllBookingResponse> getBookings() {
        return ResponseEntity.ok(getAllBookingsUseCase.getAllBookings());
    }
    @GetMapping("{id}")
    public ResponseEntity<GetBookingByIdResponse> getBooking(@PathVariable(value = "id") final long id) {
        final GetBookingByIdResponse response = getBookingByIdUseCase.getBookingById(id);
        return ResponseEntity.ok().body(response);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<DeleteBookingResponse> deleteBooking(@PathVariable long id) {
        return ResponseEntity.ok().body(deleteBookingUseCase.deleteBooking(id));
    }
    @PostMapping()
    public ResponseEntity<CreateBookingResponse> createBooking(@RequestBody @Valid CreateBookingRequest request) {
        CreateBookingResponse response = createBookingUseCase.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("{id}")
    public ResponseEntity<UpdateBookingResponse> updateBooking(@PathVariable("id") long id,
                                                       @RequestBody @Valid UpdateBookingRequest request) {
        request.setId(id);
        return ResponseEntity.ok().body(updateBookingUseCase.updateBooking(request));
    }

    @GetMapping("/bookings/filter")
    public ResponseEntity<List<Booking>> getFilteredBookings(
            @RequestParam(required = true) long garageId,
            @RequestParam(required = false) String service,
            @RequestParam(required = false, defaultValue = "false") boolean finished,
            @RequestParam(required = false, defaultValue = "false") boolean ongoing
            ) {

        String capitalizedService = service.substring(0, 1).toUpperCase() + service.substring(1).toLowerCase();
        FilterBookingRequest filterRequest = FilterBookingRequest.builder()
                .garageId(garageId)
                .service(ServiceType.valueOf(capitalizedService))
                .finished(finished)
                .ongoing(ongoing)
                .build();

        // Call a service method to get filtered bookings
        FilterBookingResponse filteredBookings = filterBookingsUseCase.filterBookings(filterRequest);

        return ResponseEntity.ok(filteredBookings.getBookings());
    }
}
