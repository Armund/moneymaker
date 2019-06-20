package itmo.foodtech.moneymaker.domain.questionResponse;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class TextareaQuestionResponse extends QuestionResponse {
    @NonNull
    private String text;
}
