package com.alasdeplata.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alasdeplata.dto.destination.DestinationRequest;
import com.alasdeplata.dto.destination.DestinationResponse;
import com.alasdeplata.dto.destination.DestinationUpdateRequest;
import com.alasdeplata.enums.Continent;
import com.alasdeplata.services.DestinationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/destinations")  //ruta base
@RequiredArgsConstructor
public class DestinationController {

    private final DestinationService destinationService;

    @GetMapping
    public ResponseEntity<List<DestinationResponse>> getAll(@RequestParam(required = false) List<String> continent) {
        List<DestinationResponse> items = new ArrayList<>();

        // Filtrado por continente si se pasa como parámetro
        if (continent != null && !continent.isEmpty()) {
            for (String c : continent) {
                try {
                    Continent continentEnum = Continent.valueOf(c.trim().toUpperCase());
                    items.addAll(destinationService.getDestinationByContinent(continentEnum));
                } catch (IllegalArgumentException e) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
        } else {
            items = destinationService.getAllDestinations();
        }

        if (items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DestinationResponse> getById(@PathVariable() Long id) {
        Optional<DestinationResponse> destinationOptional = destinationService.getDestinationById(id);

        if (destinationOptional.isPresent()) {
            return new ResponseEntity<>(destinationOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<DestinationResponse> create(@Valid @RequestBody DestinationRequest item) {
        DestinationResponse savedItem = destinationService.createDestination(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<DestinationResponse> update(@PathVariable() Long id,
            @RequestBody DestinationUpdateRequest destinationRequest) {
        Optional<DestinationResponse> destinationOptional = destinationService.getDestinationById(id);

        if (destinationOptional.isPresent()) {
            DestinationResponse updatedItem = destinationService.updateDestination(id, destinationRequest);
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable() Long id) {
        destinationService.deleteDestination(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
