package com.alasdeplata.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alasdeplata.dto.payment.PaymentRequest;
import com.alasdeplata.dto.payment.PaymentResponse;
import com.alasdeplata.dto.payment.PaymentUpdateRequest;
import com.alasdeplata.enums.PaymentStatus;
import com.alasdeplata.mapper.PaymentMapper;
import com.alasdeplata.models.Payment;
import com.alasdeplata.models.UserEntity;
import com.alasdeplata.repository.PaymentRepository;
import com.alasdeplata.repository.ReservationRepository;
import com.alasdeplata.repository.UserRepository;
import com.alasdeplata.services.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toResponse)
                .toList();
    }

    @Override
    public PaymentResponse getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .map(paymentMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @Override
    public List<PaymentResponse> getPaymentsByUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity user = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return paymentRepository.findByReservation_User_Id(user.getId()).stream()
                .map(paymentMapper::toResponse)
                .toList();
    }

    @Override
    public PaymentResponse createPayment(PaymentRequest item) {
        Payment payment = paymentMapper.toEntity(item);

        payment.setReservation(reservationRepository.findById(item.reservationId())
                .orElseThrow(() -> new RuntimeException("Reservation not found")));

        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setDescription("Pago de reserva #" + item.reservationId() + " con " + item.paymentMethod());
        Payment savedPayment = paymentRepository.save(payment);
        return paymentMapper.toResponse(savedPayment);
    }

    @Override
    public PaymentResponse updatePayment(Long id, PaymentUpdateRequest item) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        paymentMapper.updatePaymentFromDto(item, payment);

        Payment updatedPayment = paymentRepository.save(payment);
        return paymentMapper.toResponse(updatedPayment);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

}
