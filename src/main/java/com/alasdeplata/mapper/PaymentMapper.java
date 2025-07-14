package com.alasdeplata.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.alasdeplata.dto.payment.PaymentRequest;
import com.alasdeplata.dto.payment.PaymentResponse;
import com.alasdeplata.dto.payment.PaymentUpdateRequest;
import com.alasdeplata.models.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(target = "reservationId", source = "reservation.id")
    PaymentResponse toResponse(Payment payment);

    Payment toEntity(PaymentRequest data);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePaymentFromDto(PaymentUpdateRequest dto, @MappingTarget Payment entity);

}
