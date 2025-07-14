package com.alasdeplata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alasdeplata.models.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
