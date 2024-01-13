package com.rooshdashboard.rooshdashboard.business.impl.PriceList;

import com.rooshdashboard.rooshdashboard.business.DeletePriceListUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidCustomerException;
import com.rooshdashboard.rooshdashboard.domain.Customer.DeleteCustomerResponse;
import com.rooshdashboard.rooshdashboard.domain.PriceList.DeletePriceListResponse;
import com.rooshdashboard.rooshdashboard.persistance.PriceListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletePriceListUseCaseImpl implements DeletePriceListUseCase {
    private final PriceListRepository priceListRepository;

    @Override
    public DeletePriceListResponse deletePriceList(Long priceListId){
        if(priceListRepository.existsById(priceListId)) {
            this.priceListRepository.deleteById(priceListId);
            return DeletePriceListResponse.builder().message("Successfully deleted price list").build();
        }
        throw new InvalidCustomerException("CUSTOMER_NOT_FOUND");
    }
}
