package com.alasdeplata.services.impl;

import com.alasdeplata.dto.airline.AirlineRequest;
import com.alasdeplata.dto.airline.AirlineResponse;
import com.alasdeplata.dto.airline.AirlineUpdateRequest;
import com.alasdeplata.mapper.AirlineMapper;
import com.alasdeplata.models.Airline;
import com.alasdeplata.repository.AirlineRepository;
import com.alasdeplata.services.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;
    private final AirlineMapper airlineMapper;

    @Override
    public List<AirlineResponse> getAllAirlines() {
        return airlineRepository.findAll()
                .stream()
                .map(airlineMapper::toResponse)
                .toList();
    }

    @Override
    public AirlineResponse getAirlineById(Long id) {
        return airlineRepository.findById(id)
                .map(airlineMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Airline not found"));
    }

    @Override
    public AirlineResponse createAirline(AirlineRequest airlineRequest) {
        var airline = airlineMapper.toEntity(airlineRequest);
        var savedAirline = airlineRepository.save(airline);
        return airlineMapper.toResponse(savedAirline);
    }

    @Override
    public AirlineResponse updateAirline(Long id, AirlineUpdateRequest airlineRequest) {
        Airline airline = airlineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airline not found"));

        airlineMapper.updateAirlineFromDto(airlineRequest, airline);

        Airline updatedAirline = airlineRepository.save(airline);
        return airlineMapper.toResponse(updatedAirline);
    }

    @Override
    public void deleteAirline(Long id) {
        airlineRepository.deleteById(id);
    }
}
