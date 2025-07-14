package com.alasdeplata.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alasdeplata.enums.FlightClass;
import com.alasdeplata.models.Flight;
import com.alasdeplata.models.FlightPrice;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDestinationId(Long destinationId);

}

