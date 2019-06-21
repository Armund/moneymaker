package itmo.foodtech.moneymaker;

import itmo.foodtech.moneymaker.domain.Survey;
import itmo.foodtech.moneymaker.domain.SurveyMeta;
import itmo.foodtech.moneymaker.domain.SurveyResponse;
import itmo.foodtech.moneymaker.domain.dto.FullResponseDto;
import itmo.foodtech.moneymaker.domain.dto.FullSurveyDto;
import itmo.foodtech.moneymaker.domain.dto.SimplifiedSurveyDto;
import itmo.foodtech.moneymaker.domain.question.Question;
import itmo.foodtech.moneymaker.domain.question.questionSubtypes.CheckboxQuestion;
import itmo.foodtech.moneymaker.domain.question.questionSubtypes.MultipleChoiceQuestion;
import itmo.foodtech.moneymaker.domain.questionResponse.responseSubtypes.CheckboxQuestionResponse;
import itmo.foodtech.moneymaker.domain.questionResponse.responseSubtypes.MultipleChoiceQuestionResponse;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoneymakerApplicationTests {

	private ModelMapper mapper = new ModelMapper();

	@Test
	public void contextLoads() {
	}

	@Test
	public void whenConvertSurveyToFullSurveyDto() {
		mapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STRICT)
				.setFieldMatchingEnabled(true)
				.setSkipNullEnabled(true)
				.setFieldAccessLevel(PRIVATE);

		Survey survey = new Survey();
		survey.setId(ObjectId.get().toHexString());

		SurveyMeta surveyMeta = new SurveyMeta();
		surveyMeta.setCompanyId(ObjectId.get().toHexString());
		surveyMeta.setConfirmationMessage("Conf message");
		surveyMeta.setDescription("Hohoho");
		surveyMeta.setQuestionsNumber(5);
		surveyMeta.setTitle("First Title");
		surveyMeta.setEditorId(ObjectId.get().toHexString());
		survey.setMeta(surveyMeta);

		CheckboxQuestion question1 = new CheckboxQuestion();
		question1.setId(ObjectId.get().toHexString());
		question1.setHelpText("Hehe");
		question1.setPlaceholder("Hoho");
		question1.setRequired(true);
		question1.setTitle("First question");
		question1.setType(Question.QuestionType.CHECKBOX);
		question1.setOptions(Arrays.asList("First Op", "Second op", "Third op"));

		MultipleChoiceQuestion question2 = new MultipleChoiceQuestion();
		question2.setId(ObjectId.get().toHexString());
		question2.setHelpText("Huhu");
		question2.setPlaceholder("Hmhm");
		question2.setRequired(false);
		question2.setTitle("Second question");
		question2.setType(Question.QuestionType.MULTIPLE_CHOICE);
		question2.setOptions(Arrays.asList("First Option", "Second Option"));

		survey.setQuestions(Arrays.asList(question1, question2));

		FullSurveyDto surveyDto = mapper.map(survey, FullSurveyDto.class);


		assertEquals(survey.getId(), surveyDto.getId());
		assertEquals(survey.getQuestions(), surveyDto.getQuestions());
	}

	@Test
	public void whenConvertSurveyToSimplifiedSurveyDto() {
		mapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STRICT)
				.setFieldMatchingEnabled(true)
				.setSkipNullEnabled(true)
				.setFieldAccessLevel(PRIVATE);

		Survey survey = new Survey();
		survey.setId(ObjectId.get().toHexString());

		SurveyMeta surveyMeta = new SurveyMeta();
		surveyMeta.setCompanyId(ObjectId.get().toHexString());
		surveyMeta.setConfirmationMessage("Conf message");
		surveyMeta.setDescription("Hohoho");
		surveyMeta.setQuestionsNumber(5);
		surveyMeta.setTitle("First Title");
		surveyMeta.setEditorId(ObjectId.get().toHexString());
		survey.setMeta(surveyMeta);

		CheckboxQuestion question1 = new CheckboxQuestion();
		question1.setId(ObjectId.get().toHexString());
		question1.setHelpText("Hehe");
		question1.setPlaceholder("Hoho");
		question1.setRequired(true);
		question1.setTitle("First question");
		question1.setType(Question.QuestionType.CHECKBOX);
		question1.setOptions(Arrays.asList("First Op", "Second op", "Third op"));

		MultipleChoiceQuestion question2 = new MultipleChoiceQuestion();
		question2.setId(ObjectId.get().toHexString());
		question2.setHelpText("Huhu");
		question2.setPlaceholder("Hmhm");
		question2.setRequired(false);
		question2.setTitle("Second question");
		question2.setType(Question.QuestionType.MULTIPLE_CHOICE);
		question2.setOptions(Arrays.asList("First Option", "Second Option"));

		survey.setQuestions(Arrays.asList(question1, question2));

		SimplifiedSurveyDto surveyDto = mapper.map(survey, SimplifiedSurveyDto.class);


		assertEquals(survey.getId(), surveyDto.getId());
	}


	@Test
	public void whenConvertSurveyResponseToFullResponseDto() {
		mapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STRICT)
				.setFieldMatchingEnabled(true)
				.setSkipNullEnabled(true)
				.setFieldAccessLevel(PRIVATE);

		SurveyResponse response = new SurveyResponse();
		response.setId(ObjectId.get().toHexString());
		response.setSurveyId(ObjectId.get().toHexString());
		response.setCheckId(ObjectId.get().toHexString());

		CheckboxQuestionResponse response1 = new CheckboxQuestionResponse();
		response1.setSelectedAnswers(Arrays.asList("First", "Second", "Third"));
		response1.setQuestionId(ObjectId.get().toHexString());
		response1.setType(Question.QuestionType.CHECKBOX);

		MultipleChoiceQuestionResponse response2 = new MultipleChoiceQuestionResponse();
		response2.setSelectedAnswer("Fourth");
		response2.setQuestionId(ObjectId.get().toHexString());
		response2.setType(Question.QuestionType.MULTIPLE_CHOICE);

		response.setReplies(Arrays.asList(response1, response2));

		FullResponseDto surveyDto = mapper.map(response, FullResponseDto.class);


		assertEquals(response.getId(), surveyDto.getId());
	}

	@Test
	public void whenConvertSurveyResponseToSimplifiedResponseDto() {
		mapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STRICT)
				.setFieldMatchingEnabled(true)
				.setSkipNullEnabled(true)
				.setFieldAccessLevel(PRIVATE);

		Survey survey = new Survey();
		survey.setId(ObjectId.get().toHexString());

		SurveyMeta surveyMeta = new SurveyMeta();
		surveyMeta.setCompanyId(ObjectId.get().toHexString());
		surveyMeta.setConfirmationMessage("Conf message");
		surveyMeta.setDescription("Hohoho");
		surveyMeta.setQuestionsNumber(5);
		surveyMeta.setTitle("First Title");
		surveyMeta.setEditorId(ObjectId.get().toHexString());
		survey.setMeta(surveyMeta);

		CheckboxQuestion question1 = new CheckboxQuestion();
		question1.setId(ObjectId.get().toHexString());
		question1.setHelpText("Hehe");
		question1.setPlaceholder("Hoho");
		question1.setRequired(true);
		question1.setTitle("First question");
		question1.setType(Question.QuestionType.CHECKBOX);
		question1.setOptions(Arrays.asList("First Op", "Second op", "Third op"));

		MultipleChoiceQuestion question2 = new MultipleChoiceQuestion();
		question2.setId(ObjectId.get().toHexString());
		question2.setHelpText("Huhu");
		question2.setPlaceholder("Hmhm");
		question2.setRequired(false);
		question2.setTitle("Second question");
		question2.setType(Question.QuestionType.MULTIPLE_CHOICE);
		question2.setOptions(Arrays.asList("First Option", "Second Option"));

		survey.setQuestions(Arrays.asList(question1, question2));

		SimplifiedSurveyDto surveyDto = mapper.map(survey, SimplifiedSurveyDto.class);


		assertEquals(survey.getId(), surveyDto.getId());
	}
}
