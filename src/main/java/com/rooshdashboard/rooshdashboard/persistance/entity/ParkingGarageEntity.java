package com.rooshdashboard.rooshdashboard.persistance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "parking_garages")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingGarageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank
    @Column(name = "name")
    @Length(max = 50)
    private String name;
    @NotBlank
    @Column(name = "airport")
    @Length(max = 50)
    private String airport;
    @NotBlank
    @Column(name = "location")
    @Length(max = 50)
    private String location;
    @NotNull
    @Column(name = "travel_time")
    private int travelTime;
    @NotNull
    @Column(name = "travel_distance")
    private int travelDistance;
    @NotNull
    @Column(name = "phone_number")
    private int phoneNumber;
    @NotNull
    @Column(name = "booking_id")
    private int bookingId;
    @NotNull
    @NotNull
    @OneToOne
    @JoinColumn(name = "parking_garage_utility_id")
    private ParkingGarageUtilityEntity parkingGarageUtility;
}
