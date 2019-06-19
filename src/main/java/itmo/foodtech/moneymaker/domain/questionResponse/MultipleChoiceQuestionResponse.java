package itmo.foodtech.moneymaker.domain.questionResponse;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class MultipleChoiceQuestionResponse {
    @NonNull
    private String selectedAnswer;

    private boolean selectedAnotherOption;
}
