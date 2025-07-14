package com.alasdeplata.dto.reservationpassenger;

import jakarta.validation.constraints.NotNull;

public record ReservationPassengerRequest(
        @NotNull Long reservationId,
        @NotNull Long passengerId,
        @NotNull Long seatId) {

}
