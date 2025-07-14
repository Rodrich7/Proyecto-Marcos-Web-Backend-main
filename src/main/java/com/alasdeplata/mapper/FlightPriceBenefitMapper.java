package com.alasdeplata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.alasdeplata.dto.flightpricebenefits.FlightPriceBenefitResponse;
import com.alasdeplata.models.FlightPriceBenefit;

@Mapper(componentModel = "spring")
public interface FlightPriceBenefitMapper {
    @Mapping(target = "code", source = "benefit.code")
    @Mapping(target = "name", source = "benefit.name")
    @Mapping(target = "description", source = "benefit.description")
    @Mapping(target = "value", source = "value")
    FlightPriceBenefitResponse toResponse(FlightPriceBenefit entity);
}
