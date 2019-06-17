package itmo.foodtech.moneymaker.controller;

import itmo.foodtech.moneymaker.domain.Survey;
import itmo.foodtech.moneymaker.repos.SurveyRepository;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("survey")
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    @GetMapping
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @GetMapping("{id}")
    public Survey getSurvey(@PathVariable ObjectId id) {
        return surveyRepository.findById(id);
    }

    @PostMapping
    public Survey create(@NotNull @Valid @RequestBody Survey survey) {
        survey.setId(ObjectId.get());
        surveyRepository.save(survey);
        return survey;
    }

    @PutMapping("{id}")
    public void modifySurvey(@PathVariable ObjectId id, @NotNull @Valid @RequestBody Survey survey) {
        survey.setId(id);
        surveyRepository.save(survey);
    }

    @DeleteMapping("{id}")
    public void deleteSurvey(@PathVariable ObjectId id) {
        surveyRepository.delete(surveyRepository.findById(id));
    }
}
