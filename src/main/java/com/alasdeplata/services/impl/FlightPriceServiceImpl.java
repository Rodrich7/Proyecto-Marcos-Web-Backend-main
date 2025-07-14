package com.alasdeplata.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alasdeplata.dto.flightprice.FlightPriceRequest;
import com.alasdeplata.dto.flightprice.FlightPriceResponse;
import com.alasdeplata.dto.flightprice.FlightPriceUpdateRequest;
import com.alasdeplata.dto.flightpricebenefits.FlightPriceBenefitResponse;
import com.alasdeplata.enums.FlightClass;
import com.alasdeplata.mapper.FlightPriceBenefitMapper;
import com.alasdeplata.mapper.FlightPriceMapper;
import com.alasdeplata.models.FlightPrice;
import com.alasdeplata.repository.FlightPriceRepository;
import com.alasdeplata.repository.FlightRepository;
import com.alasdeplata.services.FlightPriceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightPriceServiceImpl implements FlightPriceService {

    private final FlightRepository flightRepository;
    private final FlightPriceRepository flightPriceRepository;
    private final FlightPriceMapper flightPriceMapper;
    private final FlightPriceBenefitMapper flightPriceBenefitMapper;

    @Override
    public List<FlightPriceResponse> getAllFlightPrices() {
        return flightPriceRepository.findAll().stream()
                .map(flightPriceMapper::toResponse)
                .toList();
    }

    @Override
    public FlightPriceResponse getFlightPriceById(Long id) {
        return flightPriceRepository.findById(id)
                .map(flightPriceMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Flight price not found"));
    }

    @Override
    public FlightPriceResponse createFlightPrice(FlightPriceRequest flightPriceRequest) {
        FlightPrice flightPrice = flightPriceMapper.toEntity(flightPriceRequest);

        flightPrice.setFlight(flightRepository.findById(flightPriceRequest.flightId())
                .orElseThrow(() -> new RuntimeException("Flight not found")));

        FlightPrice savedFlightPrice = flightPriceRepository.save(flightPrice);
        return flightPriceMapper.toResponse(savedFlightPrice);
    }

    @Override
    public FlightPriceResponse updateFlightPrice(Long id, FlightPriceUpdateRequest flightPriceUpdateRequest) {
        FlightPrice flightPrice = flightPriceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight price not found"));
        flightPriceMapper.updateFlightPriceFromDto(flightPriceUpdateRequest, flightPrice);
        FlightPrice updatedFlightPrice = flightPriceRepository.save(flightPrice);
        return flightPriceMapper.toResponse(updatedFlightPrice);
    }

    @Override
    public void deleteFlightPrice(Long id) {
        FlightPrice flightPrice = flightPriceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight price not found"));
        flightPriceRepository.delete(flightPrice);
    }

    @Override
    public List<FlightPriceResponse> getFlightPricesByFlightId(Long flightId) {
        return flightPriceRepository.findByFlightId(flightId).stream()
                .map(flightPriceMapper::toResponse)
                .toList();
    }

    @Override
    public List<FlightPriceBenefitResponse> getBenefitsByFlightAndClass(Long flightId, FlightClass flightClass) {
        return flightPriceRepository.findByFlightIdAndFlightClass(flightId, flightClass)
            .map(FlightPrice::getBenefits)
            .orElse(List.of())
            .stream()
            .map(flightPriceBenefitMapper::toResponse)
            .toList();
    }

   

}
