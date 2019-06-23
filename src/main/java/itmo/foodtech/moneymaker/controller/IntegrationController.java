package itmo.foodtech.moneymaker.controller;

import itmo.foodtech.moneymaker.domain.Survey;
import itmo.foodtech.moneymaker.repos.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("integration")
public class IntegrationController {

    private SurveyRepository surveyRepository;

    @Autowired
    public IntegrationController(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @GetMapping("{posterId}")
    public String getSurveyURL(@PathVariable String posterId) {
        List<Survey> surveys = surveyRepository.findAllByMetaPosterId(posterId);
        if (surveys == null) {
            return null;
        }
        return "https://foodtechmoneymaker.herokuapp.com/surveys/" + surveys.get(0).getId();
    }
}
