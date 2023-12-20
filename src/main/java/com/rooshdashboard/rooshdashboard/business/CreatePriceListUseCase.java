package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.PriceList.CreatePriceListRequest;
import com.rooshdashboard.rooshdashboard.domain.PriceList.CreatePriceListResponse;

public interface CreatePriceListUseCase {
    CreatePriceListResponse createPriceList(CreatePriceListRequest request);
}
