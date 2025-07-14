package com.alasdeplata.dto.flight;

import java.time.LocalDateTime;

import com.alasdeplata.enums.FlightStatus;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FlightRequest(
                @NotBlank String flightNumber,
                @NotNull Long originId,
                @NotNull Long destinationId,
                @NotNull @Future LocalDateTime departureTime,
                @NotNull @Future LocalDateTime arrivalTime,
                @NotNull Long airlineId,
                @NotNull FlightStatus status) {

}
