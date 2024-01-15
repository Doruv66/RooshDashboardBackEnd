package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.PriceList.DeletePriceListResponse;

public interface DeletePriceListUseCase {

    DeletePriceListResponse deletePriceList(String startDate, String endDate);
}
