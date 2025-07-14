package com.alasdeplata.services;

import java.util.List;
import java.util.Optional;

import com.alasdeplata.dto.reservationpassenger.ReservationPassengerRequest;
import com.alasdeplata.dto.reservationpassenger.ReservationPassengerResponse;
import com.alasdeplata.dto.reservationpassenger.ReservationPassengerUpdateRequest;

public interface ReservationPassengerService {

    List<ReservationPassengerResponse> getAllReservationPassengers();

    Optional<ReservationPassengerResponse> getReservationPassengerById(Long id);

    ReservationPassengerResponse createReservationPassenger(ReservationPassengerRequest request);

    ReservationPassengerResponse updateReservationPassenger(Long id, ReservationPassengerUpdateRequest request);

    void deleteReservationPassenger(Long id);

}
