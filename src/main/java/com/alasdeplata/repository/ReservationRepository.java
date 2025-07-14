package com.alasdeplata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alasdeplata.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
