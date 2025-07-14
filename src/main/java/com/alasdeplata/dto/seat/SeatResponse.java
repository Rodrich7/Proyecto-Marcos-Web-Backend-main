package com.alasdeplata.dto.seat;

import java.math.BigDecimal;

import com.alasdeplata.enums.FlightClass;
import com.alasdeplata.enums.SeatStatus;
import com.alasdeplata.enums.SeatType;

public record SeatResponse(
        Long id,
        Long flightId,
        String seatNumber,
        FlightClass flightClass,
        SeatType seatType,
        SeatStatus seatStatus,
        BigDecimal extraPrice) {

}
