package com.rooshdashboard.rooshdashboard.business.impl.PriceList;

import com.rooshdashboard.rooshdashboard.business.DeletePriceListUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidCustomerException;
import com.rooshdashboard.rooshdashboard.domain.Customer.DeleteCustomerResponse;
import com.rooshdashboard.rooshdashboard.domain.PriceList.DeletePriceListResponse;
import com.rooshdashboard.rooshdashboard.persistance.PriceListRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.PriceListEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeletePriceListUseCaseImpl implements DeletePriceListUseCase {
    private final PriceListRepository priceListRepository;

    @Override
    public DeletePriceListResponse deletePriceList(String startDate, String endDate) {
        List<PriceListEntity> priceListsToDelete = priceListRepository.findByStartDateAndEndDate(startDate, endDate);

        if (!priceListsToDelete.isEmpty()) {
            priceListRepository.deleteAll(priceListsToDelete);
            return DeletePriceListResponse.builder().message("Successfully deleted price lists").build();
        } else {
            throw new InvalidCustomerException("PRICE_LISTS_NOT_FOUND");
        }
    }
}
