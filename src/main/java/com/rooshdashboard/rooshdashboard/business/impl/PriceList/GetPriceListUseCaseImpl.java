package com.rooshdashboard.rooshdashboard.business.impl.PriceList;

import com.rooshdashboard.rooshdashboard.business.GetPriceListUseCase;
import com.rooshdashboard.rooshdashboard.domain.PriceList.GetPriceListResponse;
import com.rooshdashboard.rooshdashboard.domain.PriceList.PriceList;
import com.rooshdashboard.rooshdashboard.persistance.PriceListRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetPriceListUseCaseImpl implements GetPriceListUseCase {
    private final PriceListRepository priceListRepository;



    @Override
    public GetPriceListResponse getPriceListByParkingGarage(ParkingGarageEntity garage) {
        List<PriceList> priceLists = priceListRepository.findByGarage(garage)
                .stream()
                .map(PriceListConverter::convert)
                .toList();
        return GetPriceListResponse.builder()
                .priceLists(priceLists)
                .build();
    }

    @Override
    public GetPriceListResponse getPriceListByStartDateEndDate(String StartDate, String EndDate) {
        List<PriceList> priceLists = priceListRepository.findByStartDateAndEndDate(StartDate, EndDate)
                .stream()
                .map(PriceListConverter::convert)
                .toList();
        return GetPriceListResponse.builder()
                .priceLists(priceLists)
                .build();
    }
}
