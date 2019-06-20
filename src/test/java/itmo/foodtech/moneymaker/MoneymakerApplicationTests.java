package itmo.foodtech.moneymaker;

import itmo.foodtech.moneymaker.domain.Survey;
import itmo.foodtech.moneymaker.domain.SurveyMeta;
import itmo.foodtech.moneymaker.domain.dto.FullSurveyDto;
import itmo.foodtech.moneymaker.domain.question.Question;
import itmo.foodtech.moneymaker.domain.question.questionSubtypes.CheckboxQuestion;
import itmo.foodtech.moneymaker.domain.question.questionSubtypes.MultipleChoiceQuestion;
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
	public void whenConvertSurveyToSurveyDto() {
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
		surveyMeta.setEditorsId(Arrays.asList(ObjectId.get().toHexString(), ObjectId.get().toHexString()));
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
		surveyDto.setId(survey.getId());

		assertEquals(survey.getId(), surveyDto.getId());
		assertEquals(survey.getQuestions(), surveyDto.getQuestions());
	}



}
