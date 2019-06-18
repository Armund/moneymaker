package itmo.foodtech.moneymaker.controller;

import itmo.foodtech.moneymaker.domain.Survey;
import itmo.foodtech.moneymaker.domain.SurveyResponse;
import itmo.foodtech.moneymaker.domain.question.Question;
import itmo.foodtech.moneymaker.repos.SurveyRepository;
import itmo.foodtech.moneymaker.repos.SurveyResponseRepository;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("surveys")
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyResponseRepository responseRepository;

    @GetMapping
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @GetMapping("{surveyId}")
    public Survey getSurvey(@PathVariable ObjectId surveyId) {
        return surveyRepository.findById(surveyId);
    }

    @PostMapping
    public Survey createSurvey(@NotNull @Valid @RequestBody Survey survey) {
        survey.setId(ObjectId.get());

        List<Question> questionList = survey.getQuestions();
        for (Question question : questionList) {
            question.setId(ObjectId.get());
        }
        surveyRepository.save(survey);
        return survey;
    }

    @PutMapping("{surveyId}")
    public void modifySurvey(@PathVariable ObjectId surveyId,
                             @NotNull @Valid @RequestBody Survey survey) {
        survey.setId(surveyId);
        surveyRepository.save(survey);
    }

    @DeleteMapping("{surveyId}")
    public void deleteSurvey(@PathVariable ObjectId surveyId) {
        surveyRepository.delete(surveyRepository.findById(surveyId));
    }

    @GetMapping("{surveyId}/responses")
    public List<SurveyResponse> getAllSurveyResponses(@PathVariable ObjectId surveyId) {
        return responseRepository.findAllBySurveyId(surveyId);
    }

    @GetMapping("{surveyId}/responses/{responseId}")
    public SurveyResponse getSurveyResponse(@PathVariable ObjectId surveyId,
                                            @PathVariable ObjectId responseId) {
        return responseRepository.findBySurveyIdAndId(surveyId, responseId);
    }

    @PostMapping("{surveyId}/responses")
    public SurveyResponse createSurveyResponse(@PathVariable ObjectId surveyId,
                                               @NotNull @Valid @RequestBody SurveyResponse response) {
        response.setSurveyId(surveyId);
        response.setId(ObjectId.get());
        responseRepository.save(response);
        return response;
    }

    @PutMapping("{surveyId}/responses/{responseId}")
    public void modifySurveyResponse(@PathVariable ObjectId surveyId,
                                     @PathVariable ObjectId responseId,
                                     @NotNull @Valid @RequestBody SurveyResponse response) {
        response.setId(responseId);
        response.setSurveyId(surveyId);
        responseRepository.save(response);
    }
}