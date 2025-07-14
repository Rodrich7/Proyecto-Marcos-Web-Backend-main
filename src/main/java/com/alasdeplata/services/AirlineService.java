package com.alasdeplata.services;

import java.util.List;

import com.alasdeplata.dto.airline.AirlineRequest;
import com.alasdeplata.dto.airline.AirlineResponse;
import com.alasdeplata.dto.airline.AirlineUpdateRequest;

public interface AirlineService {

    List<AirlineResponse> getAllAirlines();

    AirlineResponse getAirlineById(Long id);

    AirlineResponse createAirline(AirlineRequest airlineRequest);

    AirlineResponse updateAirline(Long id, AirlineUpdateRequest airlineRequest);

    void deleteAirline(Long id);
}
