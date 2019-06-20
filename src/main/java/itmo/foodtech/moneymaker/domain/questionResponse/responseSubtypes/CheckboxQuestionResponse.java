package itmo.foodtech.moneymaker.domain.questionResponse.responseSubtypes;

import itmo.foodtech.moneymaker.domain.questionResponse.QuestionResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
public class CheckboxQuestionResponse extends QuestionResponse {
    @NonNull
    private List<String> selectedAnswers;

    //private boolean selectedAnotherOption;
}
