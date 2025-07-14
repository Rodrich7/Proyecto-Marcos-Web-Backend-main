package com.alasdeplata.controllers;

import java.util.List;
import java.util.Optional;

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

import com.alasdeplata.dto.airplane.AirplaneRequest;
import com.alasdeplata.dto.airplane.AirplaneResponse;
import com.alasdeplata.dto.airplane.AirplaneUpdateRequest;
import com.alasdeplata.services.AirplaneService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/airplanes")
@RequiredArgsConstructor
public class AirplaneController {
    private final AirplaneService airplaneService;

    @GetMapping
    public ResponseEntity<List<AirplaneResponse>> getAllAirplanes() {
        List<AirplaneResponse> airplanes = airplaneService.getAllAirplanes();
        return ResponseEntity.ok(airplanes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirplaneResponse> getAirplaneById(@PathVariable Long id) {
        Optional<AirplaneResponse> airplane = airplaneService.getAirplaneById(id);

        if (airplane.isPresent()) {
            return ResponseEntity.ok(airplane.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AirplaneResponse> create(@Valid @RequestBody AirplaneRequest item) {
        AirplaneResponse createdAirplane = airplaneService.createAirplane(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAirplane);
    }

    @PutMapping("{id}")
    public ResponseEntity<AirplaneResponse> update(@PathVariable() Long id,
            @RequestBody AirplaneUpdateRequest item) {
        Optional<AirplaneResponse> existingItemOptional = airplaneService.getAirplaneById(id);
        if (existingItemOptional.isPresent()) {
            AirplaneResponse updatedItem = airplaneService.updateAirplane(id, item);
            return ResponseEntity.ok(updatedItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable() Long id) {
        airplaneService.deleteAirplane(id);
        return ResponseEntity.noContent().build();
    }

}
