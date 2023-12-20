package com.rooshdashboard.rooshdashboard.domain.PriceList;

import com.rooshdashboard.rooshdashboard.domain.ParkingGarage.ParkingGarage;
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
public class PriceList {
    private Long id;
    private ParkingGarage garage;
    private String startDate;
    private String endDate;
    private Double day1Price;
    private Double day2Price;
    private Double day3Price;
    private Double day4Price;
    private Double day5Price;
    private Double day6Price;
    private Double day7Price;
    private Double day8Price;
    private Double day9Price;
    private Double day10Price;
    private Double day11Price;
    private Double day12Price;
    private Double day13Price;
    private Double day14Price;
    private Double day15Price;
    private Double day16Price;
    private Double day17Price;
    private Double day18Price;
    private Double day19Price;
    private Double day20Price;
    private Double day21Price;
    private Double day22Price;
    private Double day23Price;
    private Double day24Price;
    private Double day25Price;
    private Double day26Price;
    private Double day27Price;
    private Double day28Price;
    private Double day29Price;
    private Double day30Price;
    private Double extraDayPrice;
}
