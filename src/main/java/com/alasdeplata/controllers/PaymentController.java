package com.alasdeplata.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alasdeplata.dto.payment.PaymentRequest;
import com.alasdeplata.dto.payment.PaymentResponse;
import com.alasdeplata.dto.payment.PaymentUpdateRequest;
import com.alasdeplata.services.PaymentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAll() {
        List<PaymentResponse> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("{id}")
    public ResponseEntity<PaymentResponse> getById(@PathVariable() Long id) {
        PaymentResponse payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/user")
    public ResponseEntity<List<PaymentResponse>> getByUserAuthenticated() {
        List<PaymentResponse> payments = paymentService.getPaymentsByUserAuthenticated();
        return ResponseEntity.ok(payments);
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> create(@Valid @RequestBody PaymentRequest item) {
        PaymentResponse payment = paymentService.createPayment(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    @PutMapping("{id}")
    public ResponseEntity<PaymentResponse> update(@PathVariable() Long id,
            @RequestBody PaymentUpdateRequest item) {
        PaymentResponse updatedPayment = paymentService.updatePayment(id, item);
        return ResponseEntity.ok(updatedPayment);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable() Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

}
