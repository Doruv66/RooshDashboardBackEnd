package com.rooshdashboard.rooshdashboard.persistance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    @JoinColumn(name = "customer_id")
    private Long customerId;
    @NotNull
    @JoinColumn(name = "car_id")
    private Long carId;
    @NotNull
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @NotNull
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @NotNull
    @Column(name = "flight_number_departure")
    private Long flightNumberDeparture;
    @NotNull
    @Column(name = "flight_number_arrivel")
    private Long flightNumberArrival;
    @NotNull
    @JoinColumn(name = "garage_id")
    private Long garageId;
    @NotNull
    @Column(name = "service_id")
    private Long serviceId;
}
