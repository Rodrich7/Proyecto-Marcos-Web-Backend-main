package com.alasdeplata.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.alasdeplata.dto.flightprice.FlightPriceRequest;
import com.alasdeplata.dto.flightprice.FlightPriceResponse;
import com.alasdeplata.dto.flightprice.FlightPriceUpdateRequest;
import com.alasdeplata.models.FlightPrice;

@Mapper(componentModel = "spring", uses = { FlightPriceBenefitMapper.class })
public interface FlightPriceMapper {
    @Mapping(target = "flightId", source = "flight.id")
    @Mapping(target = "flightClass", source = "flightClass")
    @Mapping(target = "flightClassName", expression = "java(flightPrice.getFlightClass() != null ? flightPrice.getFlightClass().getDisplayName() : null)")
    FlightPriceResponse toResponse(FlightPrice flightPrice);

    FlightPrice toEntity(FlightPriceRequest data);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFlightPriceFromDto(FlightPriceUpdateRequest dto, @MappingTarget FlightPrice entity);
}
