package itmo.foodtech.moneymaker.service;

import itmo.foodtech.moneymaker.dto.meta.supportingClasses.IntegrationDto;

public interface IntegrationService {
    IntegrationDto integrationDto(String checkId);
}
