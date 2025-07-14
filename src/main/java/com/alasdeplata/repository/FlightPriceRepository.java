package com.alasdeplata.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alasdeplata.enums.FlightClass;
import com.alasdeplata.models.FlightPrice;

@Repository
public interface FlightPriceRepository extends JpaRepository<FlightPrice, Long> {
    Optional<FlightPrice> findByFlightIdAndFlightClass(Long flightId, FlightClass flightClass);
    List<FlightPrice> findByFlightId(Long flightId);
}
