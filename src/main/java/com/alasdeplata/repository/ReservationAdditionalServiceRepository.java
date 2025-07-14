package com.alasdeplata.repository;

import com.alasdeplata.models.AdditionalService;
import com.alasdeplata.models.ReservationAdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationAdditionalServiceRepository extends JpaRepository<ReservationAdditionalService, Long> {
}
