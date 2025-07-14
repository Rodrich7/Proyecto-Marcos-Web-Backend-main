package com.alasdeplata.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alasdeplata.dto.checkout.ReservationCheckoutRequest;
import com.alasdeplata.dto.reservation.ReservationRequest;
import com.alasdeplata.dto.reservation.ReservationResponse;
import com.alasdeplata.services.ReservationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getAll() {
        List<ReservationResponse> items = new ArrayList<>();

        reservationService.getAllReservations().forEach(items::add);

        if (items.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReservationResponse> getById(@PathVariable() Long id) {
        ReservationResponse item = reservationService.getReservationById(id).orElse(null);

        if (item == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> create(@Valid @RequestBody ReservationRequest reservation) {
        ReservationResponse savedItem = reservationService.createReservation(reservation);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PostMapping("/checkout")
    public ResponseEntity<ReservationResponse> checkout(@RequestBody ReservationCheckoutRequest request) {
        ReservationResponse response = reservationService.processCheckout(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<ReservationResponse> update(
            @PathVariable() Long id,
            @RequestBody ReservationRequest item) {
        ReservationResponse updatedItem = reservationService.updateReservation(id, item);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable() Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
