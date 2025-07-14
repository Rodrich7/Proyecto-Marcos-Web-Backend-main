package com.alasdeplata.dto.destination;

import com.alasdeplata.enums.Continent;

public record DestinationResponse(
        Long id,
        String city,
        String country,
        String imageUrl,
        String airportCode,
        Continent continent,
        Double latitude,
        Double longitude)

{

}
