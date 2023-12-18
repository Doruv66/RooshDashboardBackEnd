package com.rooshdashboard.rooshdashboard.persistance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @Column(name = "name")
    @Length(max = 255)
    private String name;
//    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private AccountEntity account;
    @NotBlank
    @Column(name = "airport")
    @Length(max = 255)
    private String airport;
    @NotBlank
    @Column(name = "location")
    @Length(max = 255)
    private String location;
    @NotNull
    @Column(name = "travel_time")
    private Long travelTime;
    @NotNull
    @Column(name = "travel_distance")
    private Long travelDistance;
    @NotEmpty
    @Column(name = "phone_number")
    private String phoneNumber;
    @NotNull
    @JoinColumn(name = "parking_garage_utility_id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ParkingGarageUtilityEntity parkingGarageUtility;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "image", joinColumns = @JoinColumn(name = "garage_id"))
    @Column(name = "image_path")
    private List<String> imagePaths;
}
