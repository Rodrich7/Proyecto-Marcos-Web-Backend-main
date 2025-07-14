package com.alasdeplata.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alasdeplata.dto.airline.AirlineRequest;
import com.alasdeplata.dto.airline.AirlineResponse;
import com.alasdeplata.dto.airline.AirlineUpdateRequest;
import com.alasdeplata.services.AirlineService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/airlines")
public class AirlineController {

    private final AirlineService airlineService;

    @GetMapping
    public ResponseEntity<List<AirlineResponse>> getAll() {
        return ResponseEntity.ok(airlineService.getAllAirlines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineResponse> getAirlineById(@PathVariable Long id) {
        return ResponseEntity.ok(airlineService.getAirlineById(id));
    }

    @PostMapping
    public ResponseEntity<AirlineResponse> createAirline(@RequestBody AirlineRequest airlineRequest) {
        return ResponseEntity.ok(airlineService.createAirline(airlineRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirlineResponse> updateAirline(@PathVariable Long id,
            @RequestBody AirlineUpdateRequest airlineRequest) {
        return ResponseEntity.ok(airlineService.updateAirline(id, airlineRequest));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAirline(@RequestParam Long id) {
        airlineService.deleteAirline(id);
        return ResponseEntity.noContent().build();
    }

}
