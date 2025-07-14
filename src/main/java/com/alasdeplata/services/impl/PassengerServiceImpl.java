
package com.alasdeplata.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alasdeplata.dto.passenger.PassengerRequest;
import com.alasdeplata.dto.passenger.PassengerResponse;
import com.alasdeplata.dto.passenger.PassengerUpdateRequest;
import com.alasdeplata.mapper.PassengerMapper;
import com.alasdeplata.models.Passenger;
import com.alasdeplata.repository.PassengerRepository;
import com.alasdeplata.repository.UserRepository;
import com.alasdeplata.services.PassengerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;
    private final UserRepository userRepository;

    @Override
    public List<PassengerResponse> getAllPassengers() {
        return passengerRepository.findAll()
                .stream()
                .map(passengerMapper::toResponse)
                .toList();
    }

    @Override
    public PassengerResponse getPassengerById(Long id) {
        return passengerRepository.findById(id)
                .map(passengerMapper::toResponse).orElse(null);
    }

    @Override
    public PassengerResponse createPassenger(PassengerRequest passenger) {
        Passenger entity = passengerMapper.toEntity(passenger);
        if (passenger.userId() != null) {
            entity.setUser(userRepository.findById(passenger.userId())
                    .orElseThrow(() -> new RuntimeException("User not found")));
        }
        Passenger saved = passengerRepository.save(entity);
        return passengerMapper.toResponse(saved);
    }

    @Override
    public PassengerResponse updatePassenger(Long id, PassengerUpdateRequest passenger) {
        Passenger entity = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        passengerMapper.updatePassengerFromDto(passenger, entity);
        Passenger updated = passengerRepository.save(entity);
        return passengerMapper.toResponse(updated);
    }

    @Override
    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }

}