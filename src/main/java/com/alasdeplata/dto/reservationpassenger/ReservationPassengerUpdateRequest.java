package com.alasdeplata.dto.reservationpassenger;

public record ReservationPassengerUpdateRequest(
        Long reservationId,
        Long passengerId,
        Long seatId) {

}
