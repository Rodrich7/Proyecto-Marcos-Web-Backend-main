package com.alasdeplata.controllers;

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

import com.alasdeplata.dto.reservationpassenger.ReservationPassengerRequest;
import com.alasdeplata.dto.reservationpassenger.ReservationPassengerResponse;
import com.alasdeplata.dto.reservationpassenger.ReservationPassengerUpdateRequest;
import com.alasdeplata.services.ReservationPassengerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/reservation-passengers")
@RequiredArgsConstructor
public class ReservationPassengerController {

    private final ReservationPassengerService reservationPassengerService;

    @GetMapping
    public List<ReservationPassengerResponse> getAllReservationPassengers() {
        return reservationPassengerService.getAllReservationPassengers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationPassengerResponse> getReservationPassengerById(@PathVariable Long id) {
        return reservationPassengerService.getReservationPassengerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReservationPassengerResponse> create(@Valid @RequestBody ReservationPassengerRequest item) {
        ReservationPassengerResponse created = reservationPassengerService.createReservationPassenger(item);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationPassengerResponse> update(@PathVariable Long id,
            @RequestBody ReservationPassengerUpdateRequest item) {
        ReservationPassengerResponse updated = reservationPassengerService.updateReservationPassenger(id, item);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        reservationPassengerService.deleteReservationPassenger(id);
        return ResponseEntity.noContent().build();
    }

}
