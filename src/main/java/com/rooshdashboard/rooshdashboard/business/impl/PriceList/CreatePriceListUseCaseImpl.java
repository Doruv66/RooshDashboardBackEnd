package com.rooshdashboard.rooshdashboard.business.impl.PriceList;


import com.rooshdashboard.rooshdashboard.business.CreatePriceListUseCase;
import com.rooshdashboard.rooshdashboard.domain.PriceList.CreatePriceListRequest;
import com.rooshdashboard.rooshdashboard.domain.PriceList.CreatePriceListResponse;
import com.rooshdashboard.rooshdashboard.persistance.PriceListRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.PriceListEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreatePriceListUseCaseImpl implements CreatePriceListUseCase {
    private final PriceListRepository priceListRepository;

    @Override
    public CreatePriceListResponse createPriceList(CreatePriceListRequest request) {

        PriceListEntity savedPriceList = saveNewPriceList(request);

        return CreatePriceListResponse.builder()
                .id(savedPriceList.getId())
                .build();
    }

    private PriceListEntity saveNewPriceList(CreatePriceListRequest request) {

        PriceListEntity newPriceList = PriceListEntity.builder()
                .garage(request.getGarage())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .extraDayPrice(request.getExtraDayPrice())
                .day1Price(request.getDay1Price())
                .day2Price(request.getDay2Price())
                .day3Price(request.getDay3Price())
                .day4Price(request.getDay4Price())
                .day5Price(request.getDay5Price())
                .day6Price(request.getDay6Price())
                .day7Price(request.getDay7Price())
                .day8Price(request.getDay8Price())
                .day9Price(request.getDay9Price())
                .day10Price(request.getDay10Price())
                .day11Price(request.getDay11Price())
                .day12Price(request.getDay12Price())
                .day13Price(request.getDay13Price())
                .day14Price(request.getDay14Price())
                .day15Price(request.getDay15Price())
                .day16Price(request.getDay16Price())
                .day17Price(request.getDay17Price())
                .day18Price(request.getDay18Price())
                .day19Price(request.getDay19Price())
                .day20Price(request.getDay20Price())
                .day21Price(request.getDay21Price())
                .day22Price(request.getDay22Price())
                .day23Price(request.getDay23Price())
                .day24Price(request.getDay24Price())
                .day25Price(request.getDay25Price())
                .day26Price(request.getDay26Price())
                .day27Price(request.getDay27Price())
                .day28Price(request.getDay28Price())
                .day29Price(request.getDay29Price())
                .day30Price(request.getDay30Price())
                .build();
        return priceListRepository.save(newPriceList);

    }
}
