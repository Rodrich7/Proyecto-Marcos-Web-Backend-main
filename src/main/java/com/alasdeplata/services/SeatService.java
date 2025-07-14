package com.alasdeplata.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.alasdeplata.dto.seat.SeatFilterRequest;
import com.alasdeplata.dto.seat.SeatRequest;
import com.alasdeplata.dto.seat.SeatResponse;
import com.alasdeplata.dto.seat.SeatUpdateRequest;

public interface SeatService {
    List<SeatResponse> getAllSeats();

    List<SeatResponse> getSeatsByFilter(SeatFilterRequest filter);

    Optional<SeatResponse> getSeatById(Long id);

    List<SeatResponse> getSeatsByFlightId(Long flightId);

    BigDecimal getSeatExtraById(Long seatId);

    SeatResponse createSeat(SeatRequest item);

    SeatResponse updateSeat(Long id, SeatUpdateRequest item);

    void deleteSeat(Long id);
}
