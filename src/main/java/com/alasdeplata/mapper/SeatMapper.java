package com.alasdeplata.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.alasdeplata.dto.seat.SeatRequest;
import com.alasdeplata.dto.seat.SeatResponse;
import com.alasdeplata.dto.seat.SeatUpdateRequest;
import com.alasdeplata.models.Seat;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    @Mapping(target = "flightId", source = "flight.id")
    SeatResponse toResponse(Seat seat);

    Seat toEntity(SeatRequest data);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSeatFromDto(SeatUpdateRequest dto, @MappingTarget Seat entity);
}
