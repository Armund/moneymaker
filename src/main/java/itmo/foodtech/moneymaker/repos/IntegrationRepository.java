package itmo.foodtech.moneymaker.repos;

import itmo.foodtech.moneymaker.dto.meta.supportingClasses.IntegrationDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IntegrationRepository extends MongoRepository<IntegrationDto, Long> {
    IntegrationDto findByCheckId(String checkId);
}
