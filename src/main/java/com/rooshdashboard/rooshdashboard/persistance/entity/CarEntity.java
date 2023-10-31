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
@Table(name = "cars")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank
    @Column(name = "license_plate")
    @Length(min = 2, max = 50)
    private String licensePlate;
    @NotBlank
    @Column(name = "brand")
    @Length(min = 1, max = 50)
    private String brand;
    @NotBlank
    @Column(name = "model")
    @Length(min = 1, max = 50)
    private String model;
    @NotNull
    @Column(name = "electric")
    private Boolean electric;
}


