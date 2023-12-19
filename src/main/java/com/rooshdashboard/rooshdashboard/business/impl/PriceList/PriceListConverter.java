package com.rooshdashboard.rooshdashboard.business.impl.PriceList;

import com.rooshdashboard.rooshdashboard.business.impl.ParkingGarage.ParkingGarageConverter;
import com.rooshdashboard.rooshdashboard.domain.PriceList.PriceList;
import com.rooshdashboard.rooshdashboard.persistance.entity.PriceListEntity;

public class PriceListConverter {
    private PriceListConverter() {

    }

    public static PriceList convert(PriceListEntity priceList) {
        return PriceList.builder()
                .id(priceList.getId())
                .garage(ParkingGarageConverter.convert(priceList.getGarage()))
                .startDate(priceList.getStartDate())
                .endDate(priceList.getEndDate())
                .extraDayPrice(priceList.getExtraDayPrice())
                .day1Price(priceList.getDay1Price())
                .day2Price(priceList.getDay2Price())
                .day3Price(priceList.getDay3Price())
                .day4Price(priceList.getDay4Price())
                .day5Price(priceList.getDay5Price())
                .day6Price(priceList.getDay6Price())
                .day7Price(priceList.getDay7Price())
                .day8Price(priceList.getDay8Price())
                .day9Price(priceList.getDay9Price())
                .day10Price(priceList.getDay10Price())
                .day11Price(priceList.getDay11Price())
                .day12Price(priceList.getDay12Price())
                .day13Price(priceList.getDay13Price())
                .day14Price(priceList.getDay14Price())
                .day15Price(priceList.getDay15Price())
                .day16Price(priceList.getDay16Price())
                .day17Price(priceList.getDay17Price())
                .day18Price(priceList.getDay18Price())
                .day19Price(priceList.getDay19Price())
                .day20Price(priceList.getDay20Price())
                .day21Price(priceList.getDay21Price())
                .day22Price(priceList.getDay22Price())
                .day23Price(priceList.getDay23Price())
                .day24Price(priceList.getDay24Price())
                .day25Price(priceList.getDay25Price())
                .day26Price(priceList.getDay26Price())
                .day27Price(priceList.getDay27Price())
                .day28Price(priceList.getDay28Price())
                .day29Price(priceList.getDay29Price())
                .day30Price(priceList.getDay30Price())
                .build();

    }
}
