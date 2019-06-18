package itmo.foodtech.moneymaker.repos;

import itmo.foodtech.moneymaker.domain.SurveyResponse;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SurveyResponseRepository extends MongoRepository<SurveyResponse, Long> {
    List<SurveyResponse> findAllBySurveyId(ObjectId surveyId);

    SurveyResponse findBySurveyIdAndId(ObjectId surveyId, ObjectId id);
}
