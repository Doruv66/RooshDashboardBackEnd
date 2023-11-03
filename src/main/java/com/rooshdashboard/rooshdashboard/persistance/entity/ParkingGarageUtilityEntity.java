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
@Table(name = "parking_garage_utility")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingGarageUtilityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "parking_garage_id")
    private Long parkingGarageId;
    @NotNull
    @Column(name = "toilet")
    private Boolean toilet;
    @NotNull
    @Column(name = "electric_charge_point")
    private Boolean electricChargePoint;
    @NotNull
    @Column(name = "floors")
    private int floors;
    @NotNull
    @Column(name = "parking_spaces")
    private int parkingSpaces;
    @NotNull
    @Column(name = "parking_spaces_electric")
    private int parkingSpacesElectric;

}
