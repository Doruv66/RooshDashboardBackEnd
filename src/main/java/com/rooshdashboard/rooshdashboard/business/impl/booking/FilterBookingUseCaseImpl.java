package com.rooshdashboard.rooshdashboard.business.impl.booking;

import com.rooshdashboard.rooshdashboard.business.FilterBookingsUseCase;
import com.rooshdashboard.rooshdashboard.domain.booking.Booking;
import com.rooshdashboard.rooshdashboard.domain.booking.FilterBookingRequest;
import com.rooshdashboard.rooshdashboard.domain.booking.FilterBookingResponse;
import com.rooshdashboard.rooshdashboard.persistance.BookingRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.BookingEntity;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class FilterBookingUseCaseImpl implements FilterBookingsUseCase {

    private final BookingRepository bookingRepository;

    @Override
    public FilterBookingResponse filterBookings(FilterBookingRequest request) {
        Specification<BookingEntity> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(request.getGarageId() != 0){
                predicates.add(cb.equal(root.get("garage").get("id"), request.getGarageId()));
            }
            if (request.getService() != null && !"All".equalsIgnoreCase(request.getService().toString())) {
                predicates.add(cb.equal(root.get("service").get("serviceType"), request.getService()));
            }

            if (!(request.isFinished() && request.isOngoing()) && !(request.isFinished() == request.isOngoing())) {
                if (request.isFinished() && !request.isOngoing()) {
                    // Finished bookings: endDate is before today
                    predicates.add(cb.lessThan(root.get("endDate").as(LocalDate.class), LocalDate.now()));
                } else if (!request.isFinished() && request.isOngoing()) {
                    // Ongoing bookings: endDate is today or in the future
                    predicates.add(cb.greaterThanOrEqualTo(root.get("endDate").as(LocalDate.class), LocalDate.now()));
                }
                // When both are true or both are false, no predicates are added
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        List<BookingEntity> bookingEntities = bookingRepository.findAll(spec);

        return convertToResponse(bookingEntities);
    }

    private FilterBookingResponse convertToResponse(List<BookingEntity> bookingEntities) {
        List<Booking> convertedBookings = bookingEntities.stream().map(BookingConverters::convertToBooking).toList();
        return FilterBookingResponse.builder().bookings(convertedBookings).build();
    }
}

