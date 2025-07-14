package com.alasdeplata.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alasdeplata.dto.seat.SeatFilterRequest;
import com.alasdeplata.dto.seat.SeatRequest;
import com.alasdeplata.dto.seat.SeatResponse;
import com.alasdeplata.dto.seat.SeatUpdateRequest;
import com.alasdeplata.mapper.SeatMapper;
import com.alasdeplata.models.Seat;
import com.alasdeplata.models.SeatTypeExtraPrice;
import com.alasdeplata.repository.FlightRepository;
import com.alasdeplata.repository.SeatRepository;
import com.alasdeplata.repository.SeatTypeExtraPriceRepository;
import com.alasdeplata.services.SeatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

        private final SeatRepository seatRepository;
        private final SeatMapper seatMapper;
        private final SeatTypeExtraPriceRepository seatTypeExtraPriceRepository;
        private final FlightRepository flightRepository;

        @Override
        public List<SeatResponse> getAllSeats() {
                return seatRepository.findAll().stream()
                                .map(seatMapper::toResponse)
                                .toList();
        }

        @Override
        public Optional<SeatResponse> getSeatById(Long id) {
                return seatRepository.findById(id)
                                .map(seatMapper::toResponse);
        }

        @Override
        public SeatResponse createSeat(SeatRequest item) {
                Seat seat = seatMapper.toEntity(item);

                seat.setFlight(flightRepository.findById(item.flightId())
                                .orElseThrow(() -> new RuntimeException("Flight not found")));

                Seat savedSeat = seatRepository.save(seat);
                return seatMapper.toResponse(savedSeat);
        }

        @Override
        public SeatResponse updateSeat(Long id, SeatUpdateRequest item) {
                Seat seat = seatRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Seat not found"));

                seatMapper.updateSeatFromDto(item, seat);

                Seat updatedSeat = seatRepository.save(seat);
                return seatMapper.toResponse(updatedSeat);
        }

        @Override
        public void deleteSeat(Long id) {
                seatRepository.deleteById(id);
        }

        @Override
        public List<SeatResponse> getSeatsByFlightId(Long flightId) {
                return seatRepository.findByFlightId(flightId).stream()
                                .map(seatMapper::toResponse)
                                .toList();
        }

        @Override
        public List<SeatResponse> getSeatsByFilter(SeatFilterRequest filter) {
                return seatRepository.findAll().stream()
                                .filter(seat -> filter.getFlightId() == null
                                                || seat.getFlight().getId().equals(filter.getFlightId()))
                                .filter(seat -> filter.getSeatStatus() == null
                                                || seat.getSeatStatus().name().equalsIgnoreCase(filter.getSeatStatus()))
                                .filter(seat -> filter.getSeatType() == null
                                                || seat.getSeatType().name().equalsIgnoreCase(filter.getSeatType()))
                                .map(seat -> {
                                        BigDecimal extraPrice = seatTypeExtraPriceRepository
                                                        .findBySeatType(seat.getSeatType())
                                                        .map(SeatTypeExtraPrice::getExtraPrice)
                                                        .orElse(BigDecimal.ZERO);

                                        return new SeatResponse(
                                                        seat.getId(),
                                                        seat.getFlight().getId(),
                                                        seat.getSeatNumber(),
                                                        seat.getFlightClass(),
                                                        seat.getSeatType(),
                                                        seat.getSeatStatus(),
                                                        extraPrice);
                                })
                                .toList();
        }

        @Override
        public BigDecimal getSeatExtraById(Long seatId) {
                Seat seat = seatRepository.findById(seatId)
                                .orElseThrow(() -> new RuntimeException("Seat not found"));
                return seatTypeExtraPriceRepository.findBySeatType(seat.getSeatType())
                                .map(SeatTypeExtraPrice::getExtraPrice)
                                .orElse(BigDecimal.ZERO);
        }

}
