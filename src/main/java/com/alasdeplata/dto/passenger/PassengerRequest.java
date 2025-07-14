package com.alasdeplata.dto.passenger;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PassengerRequest(
                @NotNull Long userId,
                @NotBlank String passportNumber,
                @NotBlank String nationality,
                @NotNull LocalDate birthDate) {

}
