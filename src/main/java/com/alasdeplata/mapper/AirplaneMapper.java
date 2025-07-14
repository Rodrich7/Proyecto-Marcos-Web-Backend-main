package com.alasdeplata.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.alasdeplata.dto.airplane.AirplaneRequest;
import com.alasdeplata.dto.airplane.AirplaneResponse;
import com.alasdeplata.dto.airplane.AirplaneUpdateRequest;
import com.alasdeplata.models.Airplane;

@Mapper(componentModel = "spring")
public interface AirplaneMapper {
    AirplaneResponse toResponse(Airplane airplane);

    Airplane toEntity(AirplaneRequest data);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAirplaneFromDto(AirplaneUpdateRequest item, @MappingTarget Airplane airplane);
}
