package com.alasdeplata.dto.reservation;

import java.time.LocalDateTime;

import com.alasdeplata.enums.ReservationStatus;

import jakarta.validation.constraints.NotNull;

public record ReservationRequest(
        @NotNull Long flightId
) {}
