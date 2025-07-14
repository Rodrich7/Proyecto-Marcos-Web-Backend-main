package com.alasdeplata.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.alasdeplata.dto.destination.DestinationRequest;
import com.alasdeplata.dto.destination.DestinationResponse;
import com.alasdeplata.dto.destination.DestinationUpdateRequest;
import com.alasdeplata.models.Destination;

@Mapper(componentModel = "spring")
public interface DestinationMapper {

    DestinationResponse toResponse(Destination destination);

    Destination toEntity(DestinationRequest data);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDestinationFromDto(DestinationUpdateRequest dto, @MappingTarget Destination entity);

}
