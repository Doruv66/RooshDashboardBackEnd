package com.rooshdashboard.rooshdashboard.business.impl.PriceList;

import com.rooshdashboard.rooshdashboard.business.UpdatePriceListUseCase;
import com.rooshdashboard.rooshdashboard.business.exception.InvalidPriceListException;
import com.rooshdashboard.rooshdashboard.domain.PriceList.UpdatePriceListRequest;
import com.rooshdashboard.rooshdashboard.domain.PriceList.UpdatePriceListResponse;
import com.rooshdashboard.rooshdashboard.persistance.PriceListRepository;
import com.rooshdashboard.rooshdashboard.persistance.entity.PriceListEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class UpdatePriceListUseCaseImpl implements UpdatePriceListUseCase {
    private final PriceListRepository priceListRepository;

    @Override
    public UpdatePriceListResponse updatePriceList(UpdatePriceListRequest request) {
        Optional<PriceListEntity> priceListEntityOptional = priceListRepository.findById(request.getId());
        if (priceListEntityOptional.isEmpty()) {
            throw new InvalidPriceListException("PRICELIST_ID_INVALID");
        }

        PriceListEntity priceList = priceListEntityOptional.get();
        updateFields(request, priceList);

        return UpdatePriceListResponse.builder()
                .message("Successfully update priceList with ID " + priceList.getId())
                .build();
    }

    private void updateFields(UpdatePriceListRequest request, PriceListEntity priceList) {
        priceList.setGarage(request.getGarage());
        priceList.setStartDate(request.getStartDate());
        priceList.setEndDate(request.getEndDate());
        priceList.setExtraDayPrice(request.getExtraDayPrice());
        priceList.setDay1Price(request.getDay1Price());
        priceList.setDay2Price(request.getDay2Price());
        priceList.setDay3Price(request.getDay3Price());
        priceList.setDay4Price(request.getDay4Price());
        priceList.setDay5Price(request.getDay5Price());
        priceList.setDay7Price(request.getDay7Price());
        priceList.setDay8Price(request.getDay8Price());
        priceList.setDay9Price(request.getDay9Price());
        priceList.setDay10Price(request.getDay10Price());
        priceList.setDay11Price(request.getDay11Price());
        priceList.setDay12Price(request.getDay12Price());
        priceList.setDay13Price(request.getDay13Price());
        priceList.setDay14Price(request.getDay14Price());
        priceList.setDay15Price(request.getDay15Price());
        priceList.setDay16Price(request.getDay16Price());
        priceList.setDay17Price(request.getDay17Price());
        priceList.setDay18Price(request.getDay18Price());
        priceList.setDay19Price(request.getDay19Price());
        priceList.setDay20Price(request.getDay20Price());
        priceList.setDay21Price(request.getDay21Price());
        priceList.setDay22Price(request.getDay22Price());
        priceList.setDay23Price(request.getDay23Price());
        priceList.setDay24Price(request.getDay24Price());
        priceList.setDay25Price(request.getDay25Price());
        priceList.setDay26Price(request.getDay26Price());
        priceList.setDay27Price(request.getDay27Price());
        priceList.setDay28Price(request.getDay28Price());
        priceList.setDay29Price(request.getDay29Price());
        priceList.setDay30Price(request.getDay30Price());
        priceList.setType(request.getType());

        priceListRepository.save(priceList);
    }
}
