package com.alasdeplata.dto.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.alasdeplata.enums.PaymentMethod;

import jakarta.validation.constraints.NotNull;

public record PaymentRequest(
                @NotNull Long reservationId,
                @NotNull BigDecimal amount,
                @NotNull LocalDateTime paymentDate,
                @NotNull PaymentMethod paymentMethod,
                @NotNull String currency) {

}
