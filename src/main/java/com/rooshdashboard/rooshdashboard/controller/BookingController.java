package com.rooshdashboard.rooshdashboard.controller;

import com.rooshdashboard.rooshdashboard.business.*;
import com.rooshdashboard.rooshdashboard.business.impl.booking.FilterBookingUseCaseImpl;
import com.rooshdashboard.rooshdashboard.domain.booking.*;
import com.rooshdashboard.rooshdashboard.domain.service.ServiceType;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
@CrossOrigin(origins ="http://localhost:5173")
public class BookingController {
    private final GetAllBookingsUseCase getAllBookingsUseCase;
    private final GetBookingByIdUseCase getBookingByIdUseCase;
    private final GetBookingStatisticsUseCase getBookingStatisticsUseCase;
    private final DeleteBookingUseCase deleteBookingUseCase;
    private final UpdateBookingUseCase updateBookingUseCase;
    private final CreateBookingUseCase createBookingUseCase;
    private final FilterBookingsUseCase filterBookingsUseCase;
    private final GetArrivalsDeparturesUseCase getArrivalsDeparturesUseCase;
    private final GetIntervalArrivalsDeparturesUseCase getIntervalArrivalDepartures;
    @GetMapping
    public ResponseEntity<GetAllBookingResponse> getBookings() {
        return ResponseEntity.ok(getAllBookingsUseCase.getAllBookings());
    }

    @GetMapping("/bookings/interval-arrivals-departures")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<GetArrivalsDepartures> getIntervalArrivalsDepartures(
            @RequestParam("startTime") LocalDate startTime,
            @RequestParam("endTime") LocalDate endTime,
            @RequestParam("garageId") long garageId
    ) {
        GetArrivalsDepartures arrivalsDepartures = getIntervalArrivalDepartures.getIntervalArrivalDepartures(startTime, endTime, garageId);
        return ResponseEntity.ok(arrivalsDepartures);
    }

    @GetMapping("/arrivals-departures")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<GetArrivalsDepartures> getArrivalsDeparturesForDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("garageId") long garageId
    ) {
        GetArrivalsDepartures arrivalsDepartures = getArrivalsDeparturesUseCase.getArrivalsDepartures(date, garageId);
        return ResponseEntity.ok(arrivalsDepartures);
    }

    @GetMapping("/getBookingStatistics")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<GetBookingStatisticsResponse> getBookingStatistics(GetBookingStatisticsRequest request) {
        final GetBookingStatisticsResponse response = getBookingStatisticsUseCase.getBookingStatistics(
                request.getStartDate(),
                request.getGarageId());
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<GetBookingByIdResponse> getBooking(@PathVariable(value = "id") final long id) {
        final GetBookingByIdResponse response = getBookingByIdUseCase.getBookingById(id);
        return ResponseEntity.ok().body(response);
    }
    @DeleteMapping("{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<DeleteBookingResponse> deleteBooking(@PathVariable long id) {
        return ResponseEntity.ok().body(deleteBookingUseCase.deleteBooking(id));
    }
    @PostMapping()
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<CreateBookingResponse> createBooking(@RequestBody @Valid CreateBookingRequest request) {
        CreateBookingResponse response = createBookingUseCase.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<UpdateBookingResponse> updateBooking(@PathVariable("id") long id,
                                                       @RequestBody @Valid UpdateBookingRequest request) {
        request.setId(id);
        return ResponseEntity.ok().body(updateBookingUseCase.updateBooking(request));
    }

    @GetMapping("/filter")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<List<Booking>> getFilteredBookings(
            @RequestParam(required = true) long garageId,
            @RequestParam(required = false) String service,
            @RequestParam(required = false, defaultValue = "false") boolean finished,
            @RequestParam(required = false, defaultValue = "false") boolean ongoing) {

        ServiceType serviceType = null;

        if (service != null && service.isEmpty()) {
            service = null;
        }

        if (service != null && !"all".equalsIgnoreCase(service)) {
            String capitalizedService = service.substring(0, 1).toUpperCase() + service.substring(1).toLowerCase();
            try {
                serviceType = ServiceType.valueOf(capitalizedService);
            } catch (IllegalArgumentException e) {
                // Handle the case where service string does not match any enum value
                // e.g., return an error response or default to a specific service type
            }
        }

        FilterBookingRequest filterRequest = FilterBookingRequest.builder()
                .garageId(garageId)
                .service(serviceType) // Use the parsed or null value
                .finished(finished)
                .ongoing(ongoing)
                .build();

        // Call a service method to get filtered bookings
        FilterBookingResponse filteredBookings = filterBookingsUseCase.filterBookings(filterRequest);

        return ResponseEntity.ok(filteredBookings.getBookings());
    }

}
