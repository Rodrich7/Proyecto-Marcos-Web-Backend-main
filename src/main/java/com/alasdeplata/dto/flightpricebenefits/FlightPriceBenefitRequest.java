package com.alasdeplata.dto.flightpricebenefits;

import jakarta.validation.constraints.NotNull;

public record FlightPriceBenefitRequest(
        @NotNull String code,
        @NotNull String name,
        @NotNull String description) {

}
