package com.alasdeplata.dto.destination;

import com.alasdeplata.enums.Continent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DestinationRequest(
                @NotBlank String city,
                @NotBlank String country,
                @NotBlank String imageUrl,
                @NotBlank String airportCode,
                @NotNull Continent continent,
                @NotNull Double latitude,
                @NotNull Double longitude
                )


{

}
