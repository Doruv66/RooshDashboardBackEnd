package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.PriceList.GetPriceListResponse;
import com.rooshdashboard.rooshdashboard.persistance.entity.ParkingGarageEntity;

public interface GetPriceListUseCase {

    GetPriceListResponse getPriceListByParkingGarage(ParkingGarageEntity garage);
}
