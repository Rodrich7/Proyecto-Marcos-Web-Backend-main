package com.alasdeplata.dto.seat;

import com.alasdeplata.enums.FlightClass;
import com.alasdeplata.enums.SeatStatus;
import com.alasdeplata.enums.SeatType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SeatRequest(
                @NotNull Long flightId,
                @NotBlank String seatNumber,
                @NotNull FlightClass flightClass,
                @NotBlank SeatType seatType,
                @NotBlank SeatStatus seatStatus) {

}
