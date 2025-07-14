package com.alasdeplata.services;

import com.alasdeplata.models.ReservationAdditionalService;

import java.util.List;

public interface ReservationAdditionalServiceService {
    ReservationAdditionalService createReservationAdditionalService(
            ReservationAdditionalService reservationAdditionalServiceRequest);


    ReservationAdditionalService getReservationAdditionalServiceById(Long id);

    List<ReservationAdditionalService> getAllReservationAdditionalServices();

    void deleteReservationAdditionalService(Long id);

    ReservationAdditionalService updateReservationAdditionalService(
            Long id,
            ReservationAdditionalService reservationAdditionalServiceRequest);
}
