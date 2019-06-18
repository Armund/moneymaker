package itmo.foodtech.moneymaker.domain.question;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@NoArgsConstructor
public class QuestionReply {
    @NonNull
    private ObjectId questionId;

    @NonNull
    private String text;

    @NonNull
    private boolean selectedAnotherOption;

    @NonNull
    private List<String> selectedAnswers;
}
