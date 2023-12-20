package com.rooshdashboard.rooshdashboard.domain.PriceList;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetPriceListResponse {
    private List<PriceList> priceLists;
}
