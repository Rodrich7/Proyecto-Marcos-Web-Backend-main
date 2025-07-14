package com.alasdeplata.services;

import com.alasdeplata.models.AdditionalService;

import java.util.List;

public interface AdditionalServiceService {
    AdditionalService createAdditionalService(AdditionalService additionalService);

    AdditionalService getAdditionalServiceById(Long id);

    List<AdditionalService> getAllAdditionalServices();

    void deleteAdditionalService(Long id);

    AdditionalService updateAdditionalService(Long id, AdditionalService additionalService);
}
