package com.alasdeplata.dto.flightprice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FlightPriceRequest(
                @NotNull Long flightId,
                @NotBlank String flightClass,
                @NotNull Double price) {

}
