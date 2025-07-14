package com.alasdeplata.dto.airplane;

public record AirplaneUpdateRequest(
        String model,
        Integer capacity,
        String registrationNumber) {

}
