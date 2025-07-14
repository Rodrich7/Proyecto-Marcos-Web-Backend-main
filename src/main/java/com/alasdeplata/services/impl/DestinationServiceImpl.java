package com.alasdeplata.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alasdeplata.dto.destination.DestinationRequest;
import com.alasdeplata.dto.destination.DestinationResponse;
import com.alasdeplata.dto.destination.DestinationUpdateRequest;
import com.alasdeplata.enums.Continent;
import com.alasdeplata.mapper.DestinationMapper;
import com.alasdeplata.models.Destination;
import com.alasdeplata.repository.DestinationRepository;
import com.alasdeplata.services.DestinationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;
    private final DestinationMapper destinationMapper;

    @Override
    public List<DestinationResponse> getAllDestinations() {
        return destinationRepository.findAll().stream()
                .map(destinationMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<DestinationResponse> getDestinationById(Long id) {
        return destinationRepository.findById(id)
                .map(destinationMapper::toResponse);
    }

    @Override
    public DestinationResponse createDestination(DestinationRequest destinationRequest) {
        Destination destination = destinationMapper.toEntity(destinationRequest);
        Destination savedDestination = destinationRepository.save(destination);
        return destinationMapper.toResponse(savedDestination);
    }

    @Override
    public DestinationResponse updateDestination(Long id, DestinationUpdateRequest destinationRequest) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destination not found"));

        destinationMapper.updateDestinationFromDto(destinationRequest, destination);

        Destination updatedDestination = destinationRepository.save(destination);
        return destinationMapper.toResponse(updatedDestination);
    }

    @Override
    public void deleteDestination(Long id) {
        destinationRepository.deleteById(id);
    }

    @Override
    public List<DestinationResponse> getDestinationByContinent(Continent continent) {
        
        return destinationRepository.findByContinent(continent).stream()
                .map(destinationMapper::toResponse)
                .toList();
    }

}
