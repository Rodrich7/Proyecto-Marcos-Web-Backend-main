package com.alasdeplata.dto.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.alasdeplata.enums.PaymentMethod;

public record PaymentResponse(
                Long id,
                Long reservationId,
                BigDecimal amount,
                LocalDateTime paymentDate,
                PaymentMethod paymentMethod,
                String status,
                String transactionId,
                String currency,
                String description) {

}
