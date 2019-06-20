package itmo.foodtech.moneymaker.domain.questionResponse.responseSubtypes;

import itmo.foodtech.moneymaker.domain.questionResponse.QuestionResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class TextareaQuestionResponse extends QuestionResponse {
    @NonNull
    private String text;
}
