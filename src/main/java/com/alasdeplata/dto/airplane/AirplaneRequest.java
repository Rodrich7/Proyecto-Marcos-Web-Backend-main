package com.alasdeplata.dto.airplane;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AirplaneRequest(
                @NotBlank String model,
                @NotNull @Min(1) Integer capacity,
                @NotBlank String registrationNumber) {

}
