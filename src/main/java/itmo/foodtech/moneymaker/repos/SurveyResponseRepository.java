package itmo.foodtech.moneymaker.repos;

import itmo.foodtech.moneymaker.domain.SurveyResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SurveyResponseRepository extends MongoRepository<SurveyResponse, Long> {
    List<SurveyResponse> findAllBySurveyId(String surveyId);

    SurveyResponse findBySurveyIdAndId(String surveyId, String id);
}
