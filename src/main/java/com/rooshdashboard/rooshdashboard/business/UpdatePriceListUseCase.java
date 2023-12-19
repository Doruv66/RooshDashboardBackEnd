package com.rooshdashboard.rooshdashboard.business;

import com.rooshdashboard.rooshdashboard.domain.PriceList.UpdatePriceListRequest;
import com.rooshdashboard.rooshdashboard.domain.PriceList.UpdatePriceListResponse;

public interface UpdatePriceListUseCase {
    UpdatePriceListResponse updatePriceList(UpdatePriceListRequest request);
}
