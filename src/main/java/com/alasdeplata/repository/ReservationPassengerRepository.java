package com.alasdeplata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alasdeplata.models.ReservationPassenger;

@Repository
public interface ReservationPassengerRepository extends JpaRepository<ReservationPassenger, Long> {

}
