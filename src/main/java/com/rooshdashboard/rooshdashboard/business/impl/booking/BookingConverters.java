package com.rooshdashboard.rooshdashboard.business.impl.booking;

import com.rooshdashboard.rooshdashboard.domain.booking.Booking;
import com.rooshdashboard.rooshdashboard.persistance.entity.BookingEntity;

public class BookingConverters {
    private BookingConverters(){
    }

    public static Booking convertToBooking(BookingEntity bookingEntity){
        return Booking.builder().id(bookingEntity.getId())
                .garageId(bookingEntity.getGarageId())
                .serviceId(bookingEntity.getServiceId())
                .startDate(bookingEntity.getStartDate())
                .endDate(bookingEntity.getEndDate())
                .flightNumberArrival(bookingEntity.getFlightNumberArrival())
                .flightNumberDeparture(bookingEntity.getFlightNumberDeparture())
                .customerId(bookingEntity.getCustomerId())
                .carId(bookingEntity.getCustomerId())
                .serviceId(bookingEntity.getServiceId())
                .build();

    }

    public static BookingEntity convertToBookingEntity(Booking booking){
        return BookingEntity.builder().id(booking.getId())
                .garageId(booking.getGarageId())
                .serviceId(booking.getServiceId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .flightNumberArrival(booking.getFlightNumberArrival())
                .flightNumberDeparture(booking.getFlightNumberDeparture())
                .customerId(booking.getCustomerId())
                .carId(booking.getCustomerId())
                .serviceId(booking.getServiceId())
                .build();
    }
}
