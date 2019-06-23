package itmo.foodtech.moneymaker.repos;

import itmo.foodtech.moneymaker.domain.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SurveyRepository extends MongoRepository<Survey, Long> {
    Survey findById(String id);

    List<Survey> findAllByMetaPosterId(String posterId);
}
