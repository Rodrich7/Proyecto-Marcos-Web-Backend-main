package com.alasdeplata.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alasdeplata.dto.seat.SeatFilterRequest;
import com.alasdeplata.dto.seat.SeatRequest;
import com.alasdeplata.dto.seat.SeatResponse;
import com.alasdeplata.dto.seat.SeatUpdateRequest;
import com.alasdeplata.services.SeatService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @GetMapping
    public ResponseEntity<List<SeatResponse>> getAll(@ModelAttribute SeatFilterRequest filter) {
        List<SeatResponse> seats = seatService.getSeatsByFilter(filter);
        return ResponseEntity.ok(seats);
    }

    @GetMapping("{id}")
    public ResponseEntity<SeatResponse> getById(@PathVariable() Long id) {
        Optional<SeatResponse> seat = seatService.getSeatById(id);
        return seat.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("{id}/extra")
    public ResponseEntity<BigDecimal> getSeatExtra(@PathVariable Long id) {
        BigDecimal extra = seatService.getSeatExtraById(id);
        return ResponseEntity.ok(extra);
    }

    @PostMapping
    public ResponseEntity<SeatResponse> create(@Valid @RequestBody SeatRequest item) {
        SeatResponse seat = seatService.createSeat(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(seat);
    }

    @PutMapping("{id}")
    public ResponseEntity<SeatResponse> update(@PathVariable() Long id, @RequestBody SeatUpdateRequest item) {
        SeatResponse updatedSeat = seatService.updateSeat(id, item);
        return ResponseEntity.ok(updatedSeat);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable() Long id) {
        seatService.deleteSeat(id);
        return ResponseEntity.noContent().build();
    }

}
