package com.rooshdashboard.rooshdashboard.controller;

import com.rooshdashboard.rooshdashboard.business.*;
import com.rooshdashboard.rooshdashboard.domain.booking.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
