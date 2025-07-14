package com.alasdeplata.dto.flightprice;

import java.math.BigDecimal;
import java.util.List;

import com.alasdeplata.dto.flightpricebenefits.FlightPriceBenefitResponse;
import com.alasdeplata.enums.FlightClass;

public record FlightPriceResponse(
        Long id,
        Long flightId,
        FlightClass flightClass,
        String flightClassName,
        BigDecimal price,
        List<FlightPriceBenefitResponse> benefits) {

}
