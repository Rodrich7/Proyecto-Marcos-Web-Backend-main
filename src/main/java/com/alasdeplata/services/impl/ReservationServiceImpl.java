package com.alasdeplata.services.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.alasdeplata.enums.Gender;
import com.alasdeplata.enums.PaymentMethod;
import com.alasdeplata.enums.PaymentStatus;
import com.alasdeplata.enums.ReservationStatus;
import com.alasdeplata.enums.SeatStatus;
import com.alasdeplata.models.*;
import com.alasdeplata.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alasdeplata.dto.checkout.ReservationCheckoutRequest;
import com.alasdeplata.dto.reservation.ReservationRequest;
import com.alasdeplata.dto.reservation.ReservationResponse;
import com.alasdeplata.mapper.ReservationMapper;
import com.alasdeplata.services.ReservationService;
import com.alasdeplata.models.UserEntity;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

        private final ReservationAdditionalServiceRepository reservationAdditionalServiceRepository;
        private final ReservationRepository reservationRepository;
        private final ReservationMapper reservationMapper;
        private final UserRepository userRepository;
        private final FlightRepository flightRepository;
        private final AdditionalServiceRepository additionalServiceRepository;
        private final SeatTypeExtraPriceRepository seatTypeExtraPriceRepository;
        private final FlightPriceRepository flightPriceRepository;
        private final SeatRepository seatRepository;
        private final PassengerRepository passengerRepository;
        private final ReservationPassengerRepository reservationPassengerRepository;
        private final PaymentRepository paymentRepository;

        @Override
        public List<ReservationResponse> getAllReservations() {
                return reservationRepository.findAll()
                                .stream()
                                .map(reservationMapper::toResponse)
                                .toList();
        }

        @Override
        public Optional<ReservationResponse> getReservationById(Long id) {
                return reservationRepository.findById(id)
                                .map(reservationMapper::toResponse);
        }

        @Override
        public ReservationResponse createReservation(ReservationRequest reservation) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String username = authentication.getName();

                UserEntity user = userRepository.findUserEntityByUsername(username)
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

                Flight flight = flightRepository.findById(reservation.flightId())
                        .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));

                Reservation entity = Reservation.builder()
                        .user(user)
                        .flight(flight)
                        .status(ReservationStatus.CONFIRMED) // o el status que desees por defecto
                        .build();

                Reservation saved = reservationRepository.save(entity);
                return reservationMapper.toResponse(saved);
        }


        @Override
        public void deleteReservation(Long id) {
                reservationRepository.deleteById(id);
        }

        @Override
        public ReservationResponse updateReservation(Long id, ReservationRequest reservation) {
                Reservation entity = reservationMapper.toEntity(reservation);
                entity.setId(id);
                Reservation saved = reservationRepository.save(entity);
                return reservationMapper.toResponse(saved);
        }

        @Override
        @Transactional
        public ReservationResponse processCheckout(ReservationCheckoutRequest request) {
                // Obtener usuario autenticado
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String username = authentication.getName();
                UserEntity user = userRepository.findUserEntityByUsername(username)
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

                // Validando pasajeros
                List<Passenger> passengers = request.reservation().passengers().stream()
                                .map(p -> {
                                        Passenger passenger = new Passenger();
                                        passenger.setFirstName(p.firstName());
                                        passenger.setLastName(p.lastName());
                                        passenger.setGender(Gender.valueOf(p.gender().toUpperCase()));
                                        passenger.setUser(user);
                                        passenger.setDocumentType(p.documentType());
                                        passenger.setDocumentNumber(p.documentNumber());
                                        passenger.setDocumentExpiration(p.documentExpiration());
                                        return passengerRepository.save(passenger);
                                }).toList();

                // Buscar vuelo y asiento
                Flight flight = flightRepository.findById(request.reservation().flightId())
                                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));

                Seat seat = seatRepository.findBySeatNumberAndFlightId(request.reservation().seat(), flight.getId())
                                .orElseThrow(() -> new RuntimeException("Asiento no encontrado"));

                // Calculando el precio base y extra
                BigDecimal basePrice = flightPriceRepository.findById(request.reservation().fareId())
                                .map(FlightPrice::getPrice)
                                .orElse(BigDecimal.ZERO);

                BigDecimal extra = seatTypeExtraPriceRepository.findBySeatType(seat.getSeatType())
                                .map(SeatTypeExtraPrice::getExtraPrice).orElse(BigDecimal.ZERO);

                // Calcular servicios adicionales
                List<ReservationAdditionalService> additionalServices = additionalServiceRepository
                                .findAllById(request.reservation().services())
                                .stream()
                                .map(service -> ReservationAdditionalService.builder()
                                                .additionalService(service)
                                                .reservation(null)
                                                .build())
                                .toList();

                BigDecimal servicesTotal = additionalServiceRepository.findAllById(request.reservation().services())
                                .stream()
                                .map(s -> BigDecimal.valueOf(s.getPrice()))
                                .reduce(BigDecimal.ZERO, BigDecimal::add);

                // Calcular Total
                BigDecimal total = basePrice.add(extra).add(servicesTotal);

                // Marcar el asiento como ocupado
                if (!seat.getSeatStatus().equals(SeatStatus.AVAILABLE)) {
                        throw new RuntimeException("El asiento ya está ocupado o no disponible");
                }
                seat.setSeatStatus(SeatStatus.OCCUPIED);
                seatRepository.save(seat);

                // Crear la reserva
                Reservation reservation = Reservation.builder()
                                .user(user)
                                .flight(flight)
                                .status(ReservationStatus.CONFIRMED)
                                .build();

                Reservation savedReservation = reservationRepository.save(reservation);

                for (Passenger passenger : passengers) {
                        ReservationPassenger reservationPassenger = new ReservationPassenger();
                        reservationPassenger.setReservation(savedReservation);
                        reservationPassenger.setPassenger(passenger);
                        reservationPassenger.setSeat(seat);
                        reservationPassengerRepository.save(reservationPassenger);
                }

                for (ReservationAdditionalService service : additionalServices) {
                        ReservationAdditionalService ras = new ReservationAdditionalService();
                        ras.setReservation(savedReservation);
                        ras.setAdditionalService(service.getAdditionalService());
                        reservationAdditionalServiceRepository.save(ras);
                }

                Payment payment = Payment.builder()
                                .reservation(savedReservation)
                                .amount(total)
                                .paymentDate(LocalDateTime.now())
                                .paymentMethod(PaymentMethod.valueOf(request.payment().paymentMethod()))
                                .status(PaymentStatus.PENDING)
                                .currency("USD")
                                .description("Pago de reserva #" + savedReservation.getId())
                                .build();
                paymentRepository.save(payment);

                return reservationMapper.toResponse(savedReservation);
        }

}
