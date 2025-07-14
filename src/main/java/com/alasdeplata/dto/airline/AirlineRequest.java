package com.alasdeplata.dto.airline;

import jakarta.validation.constraints.NotBlank;

public record AirlineRequest(
        @NotBlank String name) {

}
