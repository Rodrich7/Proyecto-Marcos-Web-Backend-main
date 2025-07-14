package com.alasdeplata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alasdeplata.models.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {

}
