package com.alasdeplata.services;

import com.alasdeplata.models.FlightAdditionalService;

import java.util.List;

public interface FlightAdditionalServiceService {
    FlightAdditionalService createFlightAdditionalService(FlightAdditionalService flightAdditionalService);
    FlightAdditionalService getFlightAdditionalServiceById(Long id);
    List<FlightAdditionalService> findByFlightId(Long flightId);
    FlightAdditionalService updateFlightAdditionalService(Long id, FlightAdditionalService flightAdditionalService);
    void deleteFlightAdditionalService(Long id);
}
