package com.alasdeplata.controllers;

import com.alasdeplata.models.FlightAdditionalService;
import com.alasdeplata.services.FlightAdditionalServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flight-additional-services")
@RequiredArgsConstructor
public class FlightAdditionalServiceController {
    private final FlightAdditionalServiceService service;

    @GetMapping("/flight/{flightId}")
    public List<FlightAdditionalService> getByFlight(@PathVariable Long flightId) {
        return service.findByFlightId(flightId);
    }

    @PostMapping
    public FlightAdditionalService createFlightAdditionalService(FlightAdditionalService flightAdditionalService) {
        return service.createFlightAdditionalService(flightAdditionalService);
    }

    @DeleteMapping("/{id}")
    public void deleteFlightAdditionalService(@PathVariable Long id) {
        service.deleteFlightAdditionalService(id);
    }

}
