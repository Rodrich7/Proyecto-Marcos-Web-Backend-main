package com.alasdeplata.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.alasdeplata.dto.reservationpassenger.ReservationPassengerRequest;
import com.alasdeplata.dto.reservationpassenger.ReservationPassengerResponse;
import com.alasdeplata.dto.reservationpassenger.ReservationPassengerUpdateRequest;
import com.alasdeplata.models.ReservationPassenger;

@Mapper(componentModel = "spring")
public interface ReservationPassengerMapper {
    @Mapping(target = "reservationId", source = "reservation.id")
    @Mapping(target = "passengerId", source = "passenger.id")
    @Mapping(target = "seatId", source = "seat.id")
    ReservationPassengerResponse toResponse(ReservationPassenger reservationPassenger);

    ReservationPassenger toEntity(ReservationPassengerRequest data);
    

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateReservationPassengerFromDto(ReservationPassengerUpdateRequest dto,
            @MappingTarget ReservationPassenger entity);
}
