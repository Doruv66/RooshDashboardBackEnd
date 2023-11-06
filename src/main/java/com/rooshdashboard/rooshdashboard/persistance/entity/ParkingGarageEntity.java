package com.rooshdashboard.rooshdashboard.persistance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

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
    @Column(name = "address")
    @Length(max = 50)
    private String Address;
    @ElementCollection
    @CollectionTable(
            name = "Bookings",
            joinColumns = @JoinColumn(name = "parking_garage_id")
    )
    @Column(name = "booking_id")
    private List<Long> bookingId;
    @NotNull
    @OneToOne
    @JoinColumn(name = "parking_garage_utility_id")
    private ParkingGarageUtilityEntity parkingGarageUtility;
}
