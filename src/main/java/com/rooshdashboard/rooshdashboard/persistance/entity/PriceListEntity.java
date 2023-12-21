package com.rooshdashboard.rooshdashboard.persistance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "price_list")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @JoinColumn(name = "garage_id")
    @ManyToOne
    private ParkingGarageEntity garage;

    @NotNull
    @Column(name = "start_date")
    private String startDate;

    @NotNull
    @Column(name = "end_date")
    private String endDate;

    @NotNull
    @Column(name = "day1_price")
    private Double day1Price;

    @NotNull
    @Column(name = "day2_price")
    private Double day2Price;

    @NotNull
    @Column(name = "day3_price")
    private Double day3Price;

    @NotNull
    @Column(name = "day4_price")
    private Double day4Price;

    @NotNull
    @Column(name = "day5_price")
    private Double day5Price;

    @NotNull
    @Column(name = "day6_price")
    private Double day6Price;

    @NotNull
    @Column(name = "day7_price")
    private Double day7Price;

    @NotNull
    @Column(name = "day8_price")
    private Double day8Price;

    @NotNull
    @Column(name = "day9_price")
    private Double day9Price;

    @NotNull
    @Column(name = "day10_price")
    private Double day10Price;

    @NotNull
    @Column(name = "day11_price")
    private Double day11Price;

    @NotNull
    @Column(name = "day12_price")
    private Double day12Price;

    @NotNull
    @Column(name = "day13_price")
    private Double day13Price;

    @NotNull
    @Column(name = "day14_price")
    private Double day14Price;

    @NotNull
    @Column(name = "day15_price")
    private Double day15Price;

    @NotNull
    @Column(name = "day16_price")
    private Double day16Price;

    @NotNull
    @Column(name = "day17_price")
    private Double day17Price;

    @NotNull
    @Column(name = "day18_price")
    private Double day18Price;

    @NotNull
    @Column(name = "day19_price")
    private Double day19Price;

    @NotNull
    @Column(name = "day20_price")
    private Double day20Price;

    @NotNull
    @Column(name = "day21_price")
    private Double day21Price;

    @NotNull
    @Column(name = "day22_price")
    private Double day22Price;

    @NotNull
    @Column(name = "day23_price")
    private Double day23Price;

    @NotNull
    @Column(name = "day24_price")
    private Double day24Price;

    @NotNull
    @Column(name = "day25_price")
    private Double day25Price;

    @NotNull
    @Column(name = "day26_price")
    private Double day26Price;

    @NotNull
    @Column(name = "day27_price")
    private Double day27Price;

    @NotNull
    @Column(name = "day28_price")
    private Double day28Price;

    @NotNull
    @Column(name = "day29_price")
    private Double day29Price;

    @NotNull
    @Column(name = "day30_price")
    private Double day30Price;

    @NotNull
    @Column(name = "extra_day_price")
    private Double extraDayPrice;

}
