package com.alasdeplata.repository;


import com.alasdeplata.models.FlightAdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightAdditionalServiceRepository extends JpaRepository<FlightAdditionalService, Long> {

    List<FlightAdditionalService> findByFlightId(Long flightId);
}
