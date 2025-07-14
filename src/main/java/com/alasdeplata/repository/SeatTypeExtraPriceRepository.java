package com.alasdeplata.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alasdeplata.enums.SeatType;
import com.alasdeplata.models.SeatTypeExtraPrice;

public interface SeatTypeExtraPriceRepository extends JpaRepository<SeatTypeExtraPrice, SeatType>{
    Optional<SeatTypeExtraPrice> findBySeatType(SeatType seatType);
}
