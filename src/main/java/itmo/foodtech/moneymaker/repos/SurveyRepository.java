package itmo.foodtech.moneymaker.repos;

import itmo.foodtech.moneymaker.domain.Survey;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SurveyRepository extends MongoRepository<Survey, Long> {
    Survey findById(String id);
}
