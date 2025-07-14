package com.alasdeplata.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.alasdeplata.dto.passenger.PassengerRequest;
import com.alasdeplata.dto.passenger.PassengerResponse;
import com.alasdeplata.dto.passenger.PassengerUpdateRequest;
import com.alasdeplata.models.Passenger;

@Mapper(componentModel = "spring")
public interface PassengerMapper {
    @Mapping(target = "userId", source = "user.id")
    PassengerResponse toResponse(Passenger passenger);

    Passenger toEntity(PassengerRequest data);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePassengerFromDto(PassengerUpdateRequest dto, @MappingTarget Passenger entity);
}
