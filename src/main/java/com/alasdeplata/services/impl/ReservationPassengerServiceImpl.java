package com.alasdeplata.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alasdeplata.dto.reservationpassenger.ReservationPassengerRequest;
import com.alasdeplata.dto.reservationpassenger.ReservationPassengerResponse;
import com.alasdeplata.dto.reservationpassenger.ReservationPassengerUpdateRequest;
import com.alasdeplata.mapper.ReservationPassengerMapper;
import com.alasdeplata.models.ReservationPassenger;
import com.alasdeplata.repository.PassengerRepository;
import com.alasdeplata.repository.ReservationPassengerRepository;
import com.alasdeplata.repository.ReservationRepository;
import com.alasdeplata.repository.SeatRepository;
import com.alasdeplata.services.ReservationPassengerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationPassengerServiceImpl implements ReservationPassengerService {

    private final ReservationPassengerRepository reservationPassengerRepository;
    private final ReservationPassengerMapper reservationPassengerMapper;
    private final ReservationRepository reservationRepository;
    private final PassengerRepository passengerRepository;
    private final SeatRepository seatRepository;

    @Override
    public List<ReservationPassengerResponse> getAllReservationPassengers() {
        return reservationPassengerRepository.findAll().stream()
                .map(reservationPassengerMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<ReservationPassengerResponse> getReservationPassengerById(Long id) {
        return reservationPassengerRepository.findById(id)
                .map(reservationPassengerMapper::toResponse);
    }

    @Override
    public ReservationPassengerResponse createReservationPassenger(ReservationPassengerRequest request) {
        ReservationPassenger reservationPassenger = reservationPassengerMapper.toEntity(request);

        reservationPassenger.setReservation(reservationRepository.findById(request.reservationId())
                .orElseThrow(() -> new RuntimeException("Reservation not found")));

        reservationPassenger.setPassenger(passengerRepository.findById(request.passengerId())
                .orElseThrow(() -> new RuntimeException("Passenger not found")));

        reservationPassenger.setSeat(seatRepository.findById(request.seatId())
                .orElseThrow(() -> new RuntimeException("Seat not found")));

        ReservationPassenger savedReservationPassenger = reservationPassengerRepository.save(reservationPassenger);
        return reservationPassengerMapper.toResponse(savedReservationPassenger);
    }

    @Override
    public ReservationPassengerResponse updateReservationPassenger(Long id, ReservationPassengerUpdateRequest request) {
        ReservationPassenger reservationPassenger = reservationPassengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ReservationPassenger not found"));

        reservationPassengerMapper.updateReservationPassengerFromDto(request, reservationPassenger);

        if (request.reservationId() != null) {
            reservationPassenger.setReservation(reservationRepository.findById(request.reservationId())
                    .orElseThrow(() -> new RuntimeException("Reservation not found")));
        }
        if (request.passengerId() != null) {
            reservationPassenger.setPassenger(passengerRepository.findById(request.passengerId())
                    .orElseThrow(() -> new RuntimeException("Passenger not found")));
        }
        if (request.seatId() != null) {
            reservationPassenger.setSeat(seatRepository.findById(request.seatId())
                    .orElseThrow(() -> new RuntimeException("Seat not found")));
        }

        ReservationPassenger updatedReservationPassenger = reservationPassengerRepository.save(reservationPassenger);
        return reservationPassengerMapper.toResponse(updatedReservationPassenger);
    }

    @Override
    public void deleteReservationPassenger(Long id) {
        reservationPassengerRepository.deleteById(id);
    }

}
