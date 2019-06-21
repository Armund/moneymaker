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


}
