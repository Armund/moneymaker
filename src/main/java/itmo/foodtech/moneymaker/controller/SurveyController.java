package itmo.foodtech.moneymaker.controller;

import itmo.foodtech.moneymaker.domain.Survey;
import itmo.foodtech.moneymaker.domain.SurveyResponse;
import itmo.foodtech.moneymaker.domain.dto.*;
import itmo.foodtech.moneymaker.domain.dto.meta.ResponseMeta;
import itmo.foodtech.moneymaker.domain.dto.meta.supportingClasses.CompanyDto;
import itmo.foodtech.moneymaker.domain.dto.meta.supportingClasses.EditorDto;
import itmo.foodtech.moneymaker.domain.dto.meta.supportingClasses.IntegrationDto;
import itmo.foodtech.moneymaker.domain.dto.meta.supportingClasses.OrderItem;
import itmo.foodtech.moneymaker.domain.question.Question;
import itmo.foodtech.moneymaker.repos.SurveyRepository;
import itmo.foodtech.moneymaker.repos.SurveyResponseRepository;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("surveys")
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyResponseRepository responseRepository;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public List<? extends SurveyDto> getAllSurveys(@RequestParam(value = "fullInfo", required = false,
            defaultValue = "true") boolean fullInfo) {
        List<Survey> surveys = surveyRepository.findAll();
        if (surveys == null) {
            return null;
        }
        List<String> companies = surveys.stream()
                                    .map(survey -> survey.getMeta().getCompanyId())
                                    .collect(Collectors.toList());
        List<String> editorsList = surveys.stream()
                                         .map(survey -> survey.getMeta().getEditorId())
                                         .collect(Collectors.toList());
        if (fullInfo) {
            List<FullSurveyDto> result = surveys.stream()
                                            .map(survey -> mapper.map(survey, FullSurveyDto.class))
                                            .collect(Collectors.toList());
            for (int i = 0; i < result.size(); i++) {
                result.get(i).getMeta().setCompany(
                        new CompanyDto(companies.get(i), "Some Company"));
                result.get(i).getMeta().setEditor(
                        new EditorDto(editorsList.get(i), "Some manager"));
            }
            return result;
        }
        List<SimplifiedSurveyDto> result = surveys.stream()
                                                .map(survey -> mapper.map(survey, SimplifiedSurveyDto.class))
                                                .collect(Collectors.toList());
        for (int i = 0; i < result.size(); i++) {
            result.get(i).getMeta().setCompany(
                    new CompanyDto(companies.get(i), "Some Company"));
            result.get(i).getMeta().setEditor(
                    new EditorDto(editorsList.get(i), "Some manager"));
        }
        return result;
    }

    @GetMapping("{surveyId}")
    public FullSurveyDto getSurvey(@PathVariable String surveyId) {
        Survey survey = surveyRepository.findById(surveyId);
        if (survey == null) {
            return null;
        }
        FullSurveyDto surveyDto = mapper.map(survey, FullSurveyDto.class);
        surveyDto.getMeta().setCompany(
                new CompanyDto(survey.getMeta().getCompanyId(), "Some Company"));
        surveyDto.getMeta().setEditor(
                new EditorDto(survey.getMeta().getEditorId(), "Some editor"));
        return surveyDto;
    }

    @PostMapping
    public Survey createSurvey(@NotNull @Valid @RequestBody Survey survey) {
        survey.setId(ObjectId.get().toHexString());

        List<Question> questionList = survey.getQuestions();
        for (Question question : questionList) {
            question.setId(ObjectId.get().toHexString());
        }
        surveyRepository.save(survey);
        return survey;
    }

    @PutMapping("{surveyId}")
    public void modifySurvey(@PathVariable String surveyId,
                             @NotNull @Valid @RequestBody Survey survey) {
        survey.setId(surveyId);
        surveyRepository.save(survey);
    }

    @DeleteMapping("{surveyId}")
    public void deleteSurvey(@PathVariable String surveyId) {
        surveyRepository.delete(surveyRepository.findById(surveyId));
    }

    @GetMapping("{surveyId}/responses")
    public SurvayResults getAllSurveyResponses(@PathVariable String surveyId,
                                                      @RequestParam(value = "fullInfo", required = false,
                                                              defaultValue = "true") boolean fullInfo) {
        Survey survey = surveyRepository.findById(surveyId);
        if (survey == null) {
            return null;
        }


        if (fullInfo) {
            FullSurveyDto surveyDto = mapper.map(survey, FullSurveyDto.class);
            List<SurveyResponse> responses = responseRepository.findAllBySurveyId(surveyId);
            if (responses == null) {
                return new FullSurveyResults(surveyDto, null);
            }
            surveyDto.getMeta().setCompany(
                    new CompanyDto(survey.getMeta().getCompanyId(), "Some Company"));
            surveyDto.getMeta().setEditor(
                    new EditorDto(survey.getMeta().getEditorId(), "Some editor"));

            List<FullResponseDto> result = responses.stream()
                    .map(response -> mapper.map(response, FullResponseDto.class))
                    .collect(Collectors.toList());
            for (int i = 0; i < result.size(); i++) {
                result.get(i).setMeta(new ResponseMeta(new IntegrationDto(ObjectId.get().toHexString(),
                        1000d, Arrays.asList(new OrderItem("Pasta", 1000d))),
                        10));
            }
            return new FullSurveyResults(surveyDto, result);
        }

        SimplifiedSurveyDto surveyDto = mapper.map(survey, SimplifiedSurveyDto.class);
        List<SurveyResponse> responses = responseRepository.findAllBySurveyId(surveyId);
        if (responses == null) {
            return new SimplifiedSurveyResults(surveyDto, null);
        }
        surveyDto.getMeta().setCompany(
                new CompanyDto(survey.getMeta().getCompanyId(), "Some Company"));
        surveyDto.getMeta().setEditor(
                new EditorDto(survey.getMeta().getEditorId(), "Some editor"));

        List<SimplifiedResponseDto> result = responses.stream()
                .map(response -> mapper.map(response, SimplifiedResponseDto.class))
                .collect(Collectors.toList());
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setMeta(new ResponseMeta(new IntegrationDto(ObjectId.get().toHexString(),
                    1000d, Arrays.asList(new OrderItem("Pasta", 1000d))),
                    10));
        }
        return new SimplifiedSurveyResults(surveyDto, result);
    }

    @GetMapping("{surveyId}/responses/{responseId}")
    public SurveyResult getSurveyResponse(@PathVariable String surveyId,
                                          @PathVariable String responseId) {
        Survey survey = surveyRepository.findById(surveyId);
        if (survey == null) {
            return null;
        }
        FullSurveyDto surveyDto = mapper.map(survey, FullSurveyDto.class);
        surveyDto.getMeta().setCompany(
                new CompanyDto(survey.getMeta().getCompanyId(), "Some Company"));
        surveyDto.getMeta().setEditor(
                new EditorDto(survey.getMeta().getEditorId(), "Some editor"));

        SurveyResponse response = responseRepository.findBySurveyIdAndId(surveyId, responseId);
        if (response == null) {
            return new SurveyResult(surveyDto, null);
        }
        FullResponseDto responseDto = mapper.map(response, FullResponseDto.class);
        responseDto.setMeta(new ResponseMeta(new IntegrationDto(ObjectId.get().toHexString(),
                1000d, Arrays.asList(new OrderItem("Pasta", 1000d))),
                10));
        return new SurveyResult(surveyDto, responseDto);
    }

    @PostMapping("{surveyId}/responses")
    public SurveyResponse createSurveyResponse(@PathVariable String surveyId,
                                               @NotNull @Valid @RequestBody SurveyResponse response) {
        response.setSurveyId(surveyId);
        response.setId(ObjectId.get().toHexString());
        responseRepository.save(response);
        return response;
    }

    @PutMapping("{surveyId}/responses/{responseId}")
    public void modifySurveyResponse(@PathVariable String surveyId,
                                     @PathVariable String responseId,
                                     @NotNull @Valid @RequestBody SurveyResponse response) {
        response.setId(responseId);
        response.setSurveyId(surveyId);
        responseRepository.save(response);
    }
}