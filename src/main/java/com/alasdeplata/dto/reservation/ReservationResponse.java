package com.alasdeplata.dto.reservation;

import java.time.LocalDateTime;

import com.alasdeplata.enums.ReservationStatus;

public record ReservationResponse(
        Long id,
        Long userId,
        Long flightId,
        LocalDateTime reservationDate,
        ReservationStatus status) {

}
