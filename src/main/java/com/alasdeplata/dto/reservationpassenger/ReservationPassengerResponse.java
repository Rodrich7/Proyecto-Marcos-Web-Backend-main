package com.alasdeplata.dto.reservationpassenger;

public record ReservationPassengerResponse(
        Long id,
        Long reservationId,
        Long passengerId,
        Long seatId) {

}
