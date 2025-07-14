package com.alasdeplata.controllers;

import com.alasdeplata.models.AdditionalService;
import com.alasdeplata.services.AdditionalServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/additional-services")
@RequiredArgsConstructor
public class AdditionalServiceController {

    private final AdditionalServiceService additionalServiceService;

    @GetMapping
    public List<AdditionalService> getAll() {
        return additionalServiceService.getAllAdditionalServices();
    }

    @PostMapping
    public AdditionalService create(@RequestBody AdditionalService additionalService) {
        return additionalServiceService.createAdditionalService(additionalService);
    }

    @GetMapping("/{id}")
    public AdditionalService getById(@PathVariable Long id) {
        return additionalServiceService.getAdditionalServiceById(id);
    }

    @PutMapping("/{id}")
    public AdditionalService update(@PathVariable Long id, @RequestBody AdditionalService additionalService) {
        return additionalServiceService.updateAdditionalService(id, additionalService);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        additionalServiceService.deleteAdditionalService(id);
    }
}
