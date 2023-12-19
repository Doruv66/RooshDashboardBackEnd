package com.rooshdashboard.rooshdashboard.business.impl.booking;

import com.rooshdashboard.rooshdashboard.business.impl.Customer.CustomerConverter;
import com.rooshdashboard.rooshdashboard.business.impl.ParkingGarage.ParkingGarageConverter;
import com.rooshdashboard.rooshdashboard.business.impl.car.CarConverter;
import com.rooshdashboard.rooshdashboard.business.impl.service.ServiceConverter;
import com.rooshdashboard.rooshdashboard.domain.booking.Booking;
import com.rooshdashboard.rooshdashboard.persistance.entity.BookingEntity;

public class BookingConverters {
    private BookingConverters(){
    }

    public static Booking convertToBooking(BookingEntity bookingEntity){
        return Booking.builder().id(bookingEntity.getId())
                .garage(ParkingGarageConverter.convert(bookingEntity.getGarage()))
                .service(ServiceConverter.convert(bookingEntity.getService()))
                .startDate(bookingEntity.getStartDate())
                .endDate(bookingEntity.getEndDate())
                .flightNumberArrival(bookingEntity.getFlightNumberArrival())
                .flightNumberDeparture(bookingEntity.getFlightNumberDeparture())
                .customer(CustomerConverter.convert(bookingEntity.getCustomer()))
                .car(CarConverter.convert(bookingEntity.getCar()))
                .price(bookingEntity.getPrice())
                .build();

    }
// Commented out for now, since it's not being used and can only be fixed by giving other converters similar methods

//    public static BookingEntity convertToBookingEntity(Booking booking){
//        return BookingEntity.builder().id(booking.getId())
//                .garage(booking.getGarage())
//                .service(booking.getService())
//                .startDate(booking.getStartDate())
//                .endDate(booking.getEndDate())
//                .flightNumberArrival(booking.getFlightNumberArrival())
//                .flightNumberDeparture(booking.getFlightNumberDeparture())
//                .customer(booking.getCustomer())
//                .car(booking.getCustomer())
//                .service(booking.getService())
//                .build();
//    }
}
