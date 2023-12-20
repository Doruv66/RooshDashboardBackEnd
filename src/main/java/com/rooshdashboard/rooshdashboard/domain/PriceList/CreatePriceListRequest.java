package com.rooshdashboard.rooshdashboard.domain.PriceList;

import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.ParkingGarage;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePriceListRequest {
    @NotNull
    private Long id;

    @NotNull
    private ParkingGarageEntity garage;

    @NotBlank
    private String startDate;

    @NotBlank
    private String endDate;

    @NotNull
    private Double day1Price;

    @NotNull
    private Double day2Price;

    @NotNull
    private Double day3Price;

    @NotNull
    private Double day4Price;

    @NotNull
    private Double day5Price;

    @NotNull
    private Double day6Price;

    @NotNull
    private Double day7Price;

    @NotNull
    private Double day8Price;

    @NotNull
    private Double day9Price;

    @NotNull
    private Double day10Price;

    @NotNull
    private Double day11Price;

    @NotNull
    private Double day12Price;

    @NotNull
    private Double day13Price;

    @NotNull
    private Double day14Price;

    @NotNull
    private Double day15Price;

    @NotNull
    private Double day16Price;

    @NotNull
    private Double day17Price;

    @NotNull
    private Double day18Price;

    @NotNull
    private Double day19Price;

    @NotNull
    private Double day20Price;

    @NotNull
    private Double day21Price;

    @NotNull
    private Double day22Price;

    @NotNull
    private Double day23Price;

    @NotNull
    private Double day24Price;

    @NotNull
    private Double day25Price;

    @NotNull
    private Double day26Price;

    @NotNull
    private Double day27Price;

    @NotNull
    private Double day28Price;

    @NotNull
    private Double day29Price;

    @NotNull
    private Double day30Price;

    @NotNull
    private Double extraDayPrice;
}
