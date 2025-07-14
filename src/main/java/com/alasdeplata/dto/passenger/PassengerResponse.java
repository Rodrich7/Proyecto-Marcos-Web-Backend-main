package com.alasdeplata.dto.passenger;

import java.time.LocalDate;

public record PassengerResponse(
        Long id,
        Long userId,
        String passportNumber,
        String nationality,
        LocalDate birthDate) {

}
