package com.alasdeplata.services.impl;

import com.alasdeplata.models.FlightAdditionalService;
import com.alasdeplata.repository.FlightAdditionalServiceRepository;
import com.alasdeplata.services.FlightAdditionalServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightAdditionalServiceServiceImpl implements FlightAdditionalServiceService {

    private final FlightAdditionalServiceRepository flightAdditionalServiceRepository;

    @Override
    public FlightAdditionalService createFlightAdditionalService(FlightAdditionalService flightAdditionalService) {
        return flightAdditionalServiceRepository.save(flightAdditionalService);
    }

    @Override
    public FlightAdditionalService getFlightAdditionalServiceById(Long id) {
        return flightAdditionalServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FlightAdditionalService not found"));
    }

    @Override
    public List<FlightAdditionalService> findByFlightId(Long flightId) {
        return flightAdditionalServiceRepository.findByFlightId(flightId);
    }

    @Override
    public FlightAdditionalService updateFlightAdditionalService(Long id, FlightAdditionalService flightAdditionalService) {
        FlightAdditionalService existingService = flightAdditionalServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FlightAdditionalService not found"));

        existingService.setFlight(flightAdditionalService.getFlight());
        existingService.setAdditionalService(flightAdditionalService.getAdditionalService());
        return flightAdditionalServiceRepository.save(existingService);
    }

    @Override
    public void deleteFlightAdditionalService(Long id) {
        flightAdditionalServiceRepository.deleteById(id);
    }
}
