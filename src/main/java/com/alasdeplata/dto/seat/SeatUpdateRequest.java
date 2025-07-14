package com.alasdeplata.dto.seat;

import com.alasdeplata.enums.FlightClass;

public record SeatUpdateRequest(
        Long flightId,
        String seatNumber,
        FlightClass flightClass,
        String seatType,
        String seatStatus) {

}
