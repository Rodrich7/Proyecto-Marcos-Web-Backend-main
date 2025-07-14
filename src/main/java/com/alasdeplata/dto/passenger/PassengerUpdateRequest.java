package com.alasdeplata.dto.passenger;

import java.time.LocalDate;

public record PassengerUpdateRequest(
        Long userId,
        String passportNumber,
        String nationality,
        LocalDate birthDate) {

}
