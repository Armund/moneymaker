package itmo.foodtech.moneymaker.controller;

import itmo.foodtech.moneymaker.domain.Survey;
import itmo.foodtech.moneymaker.repos.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> getSurveyURL(@PathVariable String posterId) {
        List<Survey> surveys = surveyRepository.findAllByMetaPosterId(posterId);
        if (surveys == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                "https://foodtechmoneymaker.herokuapp.com/surveys/" + surveys.get(0).getId(),
                HttpStatus.OK);
    }
}
