package com.alasdeplata.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.alasdeplata.dto.reservation.ReservationRequest;
import com.alasdeplata.dto.reservation.ReservationResponse;
import com.alasdeplata.models.Reservation;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "flightId", source = "flight.id")
    ReservationResponse toResponse(Reservation reservation);

    @Mapping(target = "id", ignore = true)
    Reservation toEntity(ReservationRequest data);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateReservationFromDto(ReservationRequest dto, @MappingTarget Reservation entity);

}
