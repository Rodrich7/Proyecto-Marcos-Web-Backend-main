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

import com.alasdeplata.dto.passenger.PassengerRequest;
import com.alasdeplata.dto.passenger.PassengerResponse;
import com.alasdeplata.dto.passenger.PassengerUpdateRequest;
import com.alasdeplata.services.PassengerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/passengers")
@RequiredArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;

    @GetMapping
    public ResponseEntity<List<PassengerResponse>> getAll() {
        List<PassengerResponse> passengers = passengerService.getAllPassengers();
        return ResponseEntity.ok(passengers);
    }

    @GetMapping("{id}")
    public ResponseEntity<PassengerResponse> getById(@PathVariable() Long id) {
        PassengerResponse passenger = passengerService.getPassengerById(id);
        return ResponseEntity.ok(passenger);
    }

    @PostMapping
    public ResponseEntity<PassengerResponse> create(@Valid @RequestBody PassengerRequest item) {
        PassengerResponse passenger = passengerService.createPassenger(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(passenger);
    }

    @PutMapping("{id}")
    public ResponseEntity<PassengerResponse> update(@PathVariable() Long id,
            @RequestBody PassengerUpdateRequest item) {
        PassengerResponse updatedPassenger = passengerService.updatePassenger(id, item);
        return ResponseEntity.ok(updatedPassenger);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable() Long id) {
        passengerService.deletePassenger(id);
        return ResponseEntity.noContent().build();
    }

}
