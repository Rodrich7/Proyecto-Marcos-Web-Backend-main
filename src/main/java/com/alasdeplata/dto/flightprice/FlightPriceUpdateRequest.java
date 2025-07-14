package com.alasdeplata.dto.flightprice;

public record FlightPriceUpdateRequest(Long flightId,
        String flightClass,
        Double price) {

}
