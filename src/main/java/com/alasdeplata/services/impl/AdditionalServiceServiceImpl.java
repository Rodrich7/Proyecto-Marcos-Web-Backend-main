package com.alasdeplata.services.impl;

import com.alasdeplata.models.AdditionalService;
import com.alasdeplata.repository.AdditionalServiceRepository;
import com.alasdeplata.services.AdditionalServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdditionalServiceServiceImpl implements AdditionalServiceService {

    private final AdditionalServiceRepository additionalServiceRepository;

    @Override
    public AdditionalService createAdditionalService(AdditionalService additionalService) {
        return additionalServiceRepository.save(additionalService);
    }


    @Override
    public AdditionalService getAdditionalServiceById(Long id) {
        return additionalServiceRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Additional service not found with id: " + id));
    }

    @Override
    public List<AdditionalService> getAllAdditionalServices() {
        return additionalServiceRepository.findAll();
    }

    @Override
    public void deleteAdditionalService(Long id) {
        additionalServiceRepository.deleteById(id);
    }

    @Override
    public AdditionalService updateAdditionalService(Long id, AdditionalService additionalService) {
        return null;
    }
}
