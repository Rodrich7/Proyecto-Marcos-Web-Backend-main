package com.alasdeplata.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alasdeplata.dto.airplane.AirplaneRequest;
import com.alasdeplata.dto.airplane.AirplaneResponse;
import com.alasdeplata.dto.airplane.AirplaneUpdateRequest;
import com.alasdeplata.mapper.AirplaneMapper;
import com.alasdeplata.models.Airplane;
import com.alasdeplata.repository.AirplaneRepository;
import com.alasdeplata.services.AirplaneService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AirplaneServiceImpl implements AirplaneService {

    private final AirplaneRepository airplaneRepository;
    private final AirplaneMapper airplaneMapper;

    @Override
    public List<AirplaneResponse> getAllAirplanes() {
        return airplaneRepository.findAll().stream()
                .map(airplaneMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<AirplaneResponse> getAirplaneById(Long id) {
        return airplaneRepository.findById(id)
                .map(airplaneMapper::toResponse);
    }

    @Override
    public AirplaneResponse createAirplane(AirplaneRequest item) {
        Airplane airplane = airplaneMapper.toEntity(item);
        Airplane savedAirplane = airplaneRepository.save(airplane);
        return airplaneMapper.toResponse(savedAirplane);
    }

    @Override
    public AirplaneResponse updateAirplane(Long id, AirplaneUpdateRequest item) {
        Airplane airplane = airplaneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airplane with id " + id + " does not exist."));
        airplaneMapper.updateAirplaneFromDto(item, airplane);
        Airplane updatedAirplane = airplaneRepository.save(airplane);
        return airplaneMapper.toResponse(updatedAirplane);
    }

    @Override
    public void deleteAirplane(Long id) {
        if (!airplaneRepository.existsById(id)) {
            throw new IllegalArgumentException("Airplane with id " + id + " does not exist.");
        }
        airplaneRepository.deleteById(id);
    }

}
