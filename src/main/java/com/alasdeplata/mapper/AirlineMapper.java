package com.alasdeplata.mapper;

import com.alasdeplata.dto.airline.AirlineUpdateRequest;
import org.mapstruct.*;
import com.alasdeplata.dto.airline.AirlineRequest;
import com.alasdeplata.dto.airline.AirlineResponse;
import com.alasdeplata.models.Airline;

@Mapper(componentModel = "spring")
public interface AirlineMapper {

    AirlineResponse toResponse(Airline airline);

    Airline toEntity(AirlineRequest data);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAirlineFromDto(AirlineUpdateRequest item, @MappingTarget Airline airline);

}
