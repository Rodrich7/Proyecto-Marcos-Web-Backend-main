package com.alasdeplata.controllers;

import com.alasdeplata.models.ReservationAdditionalService;
import com.alasdeplata.services.ReservationAdditionalServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation-additional-services")
@RequiredArgsConstructor
public class ReservationAdditionalServiceController {

    private final ReservationAdditionalServiceService reservationAdditionalServiceService;

    @GetMapping()
    public List<ReservationAdditionalService> getAll() {
        return reservationAdditionalServiceService.getAllReservationAdditionalServices();
    }

    @GetMapping("{id}")
    public ReservationAdditionalService getById(@PathVariable Long id) {
        return reservationAdditionalServiceService.getReservationAdditionalServiceById(id);
    }

    @PostMapping
    public ReservationAdditionalService create(@RequestBody ReservationAdditionalService reservationAdditionalService) {
        return reservationAdditionalServiceService.createReservationAdditionalService(reservationAdditionalService);
    }

    @PutMapping("{id}")
    public ReservationAdditionalService update(@PathVariable Long id, @RequestBody ReservationAdditionalService reservationAdditionalService) {
        return reservationAdditionalServiceService.updateReservationAdditionalService(id, reservationAdditionalService);
    }

    public void delete(@PathVariable Long id) {
        reservationAdditionalServiceService.deleteReservationAdditionalService(id);
    }

}
