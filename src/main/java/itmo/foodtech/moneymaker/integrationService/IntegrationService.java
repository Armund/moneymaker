package itmo.foodtech.moneymaker.integrationService;

import itmo.foodtech.moneymaker.domain.dto.meta.supportingClasses.IntegrationDto;

public interface IntegrationService {
    IntegrationDto integrationDto(String checkId);
}
