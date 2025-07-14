package com.alasdeplata.dto.flight;

import java.time.LocalDateTime;

public record FlightDetailsResponse(
                Long id,
                String flightNumber,
                String airline,
                String origin,
                String airportCodeOrigin,
                String destination,
                String airportCodeDestination,
                LocalDateTime departureTime,
                LocalDateTime arrivalTime,
                Double flightPrice,
                String duration) {
}
