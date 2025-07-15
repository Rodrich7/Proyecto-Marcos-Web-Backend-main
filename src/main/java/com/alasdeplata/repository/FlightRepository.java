package com.alasdeplata.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alasdeplata.models.Flight;


@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDestinationId(Long destinationId);
    Optional<Flight> findFirstByDestination_CityIgnoreCaseAndDepartureTimeBetween(
            String ciudad,
            java.time.LocalDateTime inicio,
            java.time.LocalDateTime fin
    );
}

