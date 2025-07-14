package com.alasdeplata.services;

import java.util.List;
import java.util.Optional;

import com.alasdeplata.dto.checkout.ReservationCheckoutRequest;
import com.alasdeplata.dto.reservation.ReservationRequest;
import com.alasdeplata.dto.reservation.ReservationResponse;

public interface ReservationService {
    List<ReservationResponse> getAllReservations();

    Optional<ReservationResponse> getReservationById(Long id);

    ReservationResponse createReservation(ReservationRequest reservation);

    ReservationResponse processCheckout(ReservationCheckoutRequest request);

    ReservationResponse updateReservation(Long id, ReservationRequest reservation);

    void deleteReservation(Long id);
}
