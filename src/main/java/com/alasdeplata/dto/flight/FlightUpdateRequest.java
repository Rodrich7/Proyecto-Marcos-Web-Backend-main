
package com.alasdeplata.dto.flight;

import java.time.LocalDateTime;
import com.alasdeplata.enums.FlightStatus;

public record FlightUpdateRequest(
        String flightNumber,
        Long originId,
        Long destinationId,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        Long airlineId,
        FlightStatus status) {
}