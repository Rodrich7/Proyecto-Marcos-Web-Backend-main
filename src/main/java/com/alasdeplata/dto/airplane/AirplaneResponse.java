package com.alasdeplata.dto.airplane;

public record AirplaneResponse(
        Long id,
        String model,
        Integer capacity,
        String registrationNumber) {
}