package com.alasdeplata.services;

import java.util.List;
import java.util.Optional;

import com.alasdeplata.dto.airplane.AirplaneRequest;
import com.alasdeplata.dto.airplane.AirplaneResponse;
import com.alasdeplata.dto.airplane.AirplaneUpdateRequest;

public interface AirplaneService {
    List<AirplaneResponse> getAllAirplanes();

    Optional<AirplaneResponse> getAirplaneById(Long id);

    AirplaneResponse createAirplane(AirplaneRequest item);

    AirplaneResponse updateAirplane(Long id, AirplaneUpdateRequest item);

    void deleteAirplane(Long id);
}
