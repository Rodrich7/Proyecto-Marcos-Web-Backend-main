package com.alasdeplata.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.alasdeplata.dto.flight.FlightDetailsResponse;
import com.alasdeplata.dto.flight.FlightRequest;
import com.alasdeplata.dto.flight.FlightResponse;
import com.alasdeplata.dto.flight.FlightUpdateRequest;
import com.alasdeplata.models.Flight;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    @Mapping(
            target = "airline",
            expression = "java(flight.getAirplane() != null && flight.getAirplane().getAirline() != null ? flight.getAirplane().getAirline().getName() : null)"
    )
    @Mapping(
            target = "origin",
            expression = "java(flight.getOrigin() != null ? flight.getOrigin().getCity() : null)"
    )
    @Mapping(
            target = "destination",
            expression = "java(flight.getDestination() != null ? flight.getDestination().getCity() : null)"
    )
    @Mapping(
            target = "airportCodeOrigin",
            expression = "java(flight.getOrigin() != null ? flight.getOrigin().getAirportCode() : null)"
    )
    @Mapping(
            target = "airportCodeDestination",
            expression = "java(flight.getDestination() != null ? flight.getDestination().getAirportCode() : null)"
    )
    @Mapping(target = "flightPrice", ignore = true)
    @Mapping(target = "duration", ignore = true)
    FlightDetailsResponse toDetailsResponse(Flight flight);

    @Mapping(target = "originId", source = "origin.id")
    @Mapping(target = "destinationId", source = "destination.id")
    @Mapping(target = "airplaneId", source = "airplane.id")
    FlightResponse toResponse(Flight flight);

    @Mapping(target = "id", ignore = true)
    Flight toEntity(FlightRequest data);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFlightFromDto(FlightUpdateRequest dto, @MappingTarget Flight entity);
}
