package com.alasdeplata.services;

import java.util.List;

import com.alasdeplata.dto.flightprice.FlightPriceRequest;
import com.alasdeplata.dto.flightprice.FlightPriceResponse;
import com.alasdeplata.dto.flightprice.FlightPriceUpdateRequest;
import com.alasdeplata.dto.flightpricebenefits.FlightPriceBenefitResponse;
import com.alasdeplata.enums.FlightClass;

public interface FlightPriceService {

    List<FlightPriceResponse> getAllFlightPrices();

    List<FlightPriceBenefitResponse> getBenefitsByFlightAndClass(Long flightId, FlightClass flightClass);

    FlightPriceResponse getFlightPriceById(Long id);

    FlightPriceResponse createFlightPrice(FlightPriceRequest flightPriceRequest);

    FlightPriceResponse updateFlightPrice(Long id, FlightPriceUpdateRequest flightPriceUpdateRequest);

    void deleteFlightPrice(Long id);

    List<FlightPriceResponse> getFlightPricesByFlightId(Long flightId);
}
