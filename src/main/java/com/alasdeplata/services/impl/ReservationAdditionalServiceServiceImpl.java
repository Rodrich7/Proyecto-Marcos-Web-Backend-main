package com.alasdeplata.services.impl;

import com.alasdeplata.models.ReservationAdditionalService;
import com.alasdeplata.repository.ReservationAdditionalServiceRepository;
import com.alasdeplata.services.ReservationAdditionalServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReservationAdditionalServiceServiceImpl implements ReservationAdditionalServiceService {

    private final ReservationAdditionalServiceRepository additionalServiceRepository;


    @Override
    public ReservationAdditionalService createReservationAdditionalService(ReservationAdditionalService reservationAdditionalServiceRequest) {
        return additionalServiceRepository.save(reservationAdditionalServiceRequest);
    }

    @Override
    public ReservationAdditionalService getReservationAdditionalServiceById(Long id) {
        return additionalServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ReservationAdditionalService not found"));
    }

    @Override
    public List<ReservationAdditionalService> getAllReservationAdditionalServices() {
        return additionalServiceRepository.findAll();
    }

    @Override
    public void deleteReservationAdditionalService(Long id) {
        additionalServiceRepository.deleteById(id);
    }

    @Override
    public ReservationAdditionalService updateReservationAdditionalService(Long id, ReservationAdditionalService reservationAdditionalServiceRequest) {
        return null;
    }
}
