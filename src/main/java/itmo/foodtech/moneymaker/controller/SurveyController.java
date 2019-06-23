package itmo.foodtech.moneymaker.controller;

import itmo.foodtech.moneymaker.domain.Survey;
import itmo.foodtech.moneymaker.domain.SurveyResponse;
import itmo.foodtech.moneymaker.domain.question.Question;
import itmo.foodtech.moneymaker.dto.meta.ResponseMeta;
import itmo.foodtech.moneymaker.dto.meta.supportingClasses.CompanyDto;
import itmo.foodtech.moneymaker.dto.meta.supportingClasses.EditorDto;
import itmo.foodtech.moneymaker.dto.meta.supportingClasses.IntegrationDto;
import itmo.foodtech.moneymaker.dto.response.FullResponseDto;
import itmo.foodtech.moneymaker.dto.response.SimplifiedResponseDto;
import itmo.foodtech.moneymaker.dto.survey.FullSurveyDto;
import itmo.foodtech.moneymaker.dto.survey.SimplifiedSurveyDto;
import itmo.foodtech.moneymaker.dto.survey.SurveyDto;
import itmo.foodtech.moneymaker.dto.survey.finalDto.FullSurveyResults;
import itmo.foodtech.moneymaker.dto.survey.finalDto.SimplifiedSurveyResults;
import itmo.foodtech.moneymaker.dto.survey.finalDto.SurveyResult;
import itmo.foodtech.moneymaker.dto.survey.finalDto.SurveyResults;
import itmo.foodtech.moneymaker.repos.IntegrationRepository;
import itmo.foodtech.moneymaker.repos.SurveyRepository;
import itmo.foodtech.moneymaker.repos.SurveyResponseRepository;
import itmo.foodtech.moneymaker.service.impl.PosterIntegrationService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("surveys")
public class SurveyController {

    private SurveyRepository surveyRepository;

    private SurveyResponseRepository responseRepository;

    private ModelMapper surveyMapper;

    private PosterIntegrationService posterIntegrationService;

    private IntegrationRepository integrationRepository;

    @Autowired
    public SurveyController(SurveyRepository surveyRepository,
                            SurveyResponseRepository responseRepository,
                            IntegrationRepository integrationRepository,
                            ModelMapper surveyMapper,
                            PosterIntegrationService posterIntegrationService ) {
        this.surveyRepository = surveyRepository;
        this.responseRepository = responseRepository;
        this.integrationRepository = integrationRepository;
        this.surveyMapper = surveyMapper;
        this.posterIntegrationService = posterIntegrationService;
    }

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
                                            .map(survey -> surveyMapper.map(survey, FullSurveyDto.class))
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
                                                .map(survey -> surveyMapper.map(survey, SimplifiedSurveyDto.class))
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
        FullSurveyDto surveyDto = surveyMapper.map(survey, FullSurveyDto.class);
        surveyDto.getMeta().setCompany(
                new CompanyDto(survey.getMeta().getCompanyId(), "Some Company"));
        surveyDto.getMeta().setEditor(
                new EditorDto(survey.getMeta().getEditorId(), "Some editor"));
        return surveyDto;
    }

    @PostMapping
    public Survey createSurvey(@Valid @RequestBody Survey survey) {
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
                             @Valid @RequestBody Survey survey) {
        survey.setId(surveyId);
        for (Question question: survey.getQuestions()) {
            if (question.getId() == null) {
                question.setId(ObjectId.get().toHexString());
            }
        }
        surveyRepository.save(survey);
    }

    @DeleteMapping("{surveyId}")
    public void deleteSurvey(@PathVariable String surveyId) {
        surveyRepository.delete(surveyRepository.findById(surveyId));
    }

    @GetMapping("{surveyId}/responses")
    public SurveyResults getAllSurveyResponses(@PathVariable String surveyId,
                                               @RequestParam(value = "fullInfo", required = false,
                                                              defaultValue = "true") boolean fullInfo) {
        Survey survey = surveyRepository.findById(surveyId);
        if (survey == null) {
            return null;
        }

        if (fullInfo) {
            FullSurveyDto surveyDto = surveyMapper.map(survey, FullSurveyDto.class);

            List<SurveyResponse> responses = responseRepository.findAllBySurveyId(surveyId);
            if (responses == null) {
                return new FullSurveyResults(surveyDto, null);
            }
            surveyDto.getMeta().setCompany(
                    new CompanyDto(survey.getMeta().getCompanyId(), "Some Company"));
            surveyDto.getMeta().setEditor(
                    new EditorDto(survey.getMeta().getEditorId(), "Some editor"));

            List<FullResponseDto> result = responses.stream()
                    .map(response -> surveyMapper.map(response, FullResponseDto.class))
                    .collect(Collectors.toList());
            for (int i = 0; i < result.size(); i++) {
                result.get(i).setMeta(new ResponseMeta(integrationRepository.findByCheckId(responses.get(i)
                                .getCheckId()), responses.get(i).getAnsweredQuestionsNumber()));
            }
            return new FullSurveyResults(surveyDto, result);
        }

        SimplifiedSurveyDto surveyDto = surveyMapper.map(survey, SimplifiedSurveyDto.class);
        List<SurveyResponse> responses = responseRepository.findAllBySurveyId(surveyId);
        if (responses == null) {
            return new SimplifiedSurveyResults(surveyDto, null);
        }
        surveyDto.getMeta().setCompany(
                new CompanyDto(survey.getMeta().getCompanyId(), "Some Company"));
        surveyDto.getMeta().setEditor(
                new EditorDto(survey.getMeta().getEditorId(), "Some editor"));

        List<SimplifiedResponseDto> result = responses.stream()
                .map(response -> surveyMapper.map(response, SimplifiedResponseDto.class))
                .collect(Collectors.toList());
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setMeta(new ResponseMeta(integrationRepository.findByCheckId(responses.get(i)
                    .getCheckId()), responses.get(i).getAnsweredQuestionsNumber()));
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
        FullSurveyDto surveyDto = surveyMapper.map(survey, FullSurveyDto.class);
        surveyDto.getMeta().setCompany(
                new CompanyDto(survey.getMeta().getCompanyId(), "Some Company"));
        surveyDto.getMeta().setEditor(
                new EditorDto(survey.getMeta().getEditorId(), "Some editor"));

        SurveyResponse response = responseRepository.findBySurveyIdAndId(surveyId, responseId);
        if (response == null) {
            return new SurveyResult(surveyDto, null);
        }
        FullResponseDto responseDto = surveyMapper.map(response, FullResponseDto.class);
        responseDto.setMeta(new ResponseMeta(integrationRepository.findByCheckId(response
                                .getCheckId()), response.getAnsweredQuestionsNumber()));
        return new SurveyResult(surveyDto, responseDto);
    }

    @PostMapping("{surveyId}/responses")
    public SurveyResponse createSurveyResponse(@PathVariable String surveyId,
                                               @Valid @RequestBody SurveyResponse response) {
        response.setSurveyId(surveyId);
        response.setId(ObjectId.get().toHexString());
        responseRepository.save(response);

        IntegrationDto integrationData =
                posterIntegrationService.integrationDto(response.getCheckId());
        if (integrationData != null) {
            integrationRepository.save(integrationData);
        }
        return response;
    }

    @PutMapping("{surveyId}/responses/{responseId}")
    public void modifySurveyResponse(@PathVariable String surveyId,
                                     @PathVariable String responseId,
                                     @Valid @RequestBody SurveyResponse response) {
        response.setId(responseId);
        response.setSurveyId(surveyId);
        responseRepository.save(response);
    }
}