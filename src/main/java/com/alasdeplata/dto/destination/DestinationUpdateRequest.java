package com.alasdeplata.dto.destination;

public record DestinationUpdateRequest(
        String city,
        String country,
        String airportCode) {

}
