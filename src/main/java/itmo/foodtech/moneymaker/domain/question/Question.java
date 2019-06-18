package itmo.foodtech.moneymaker.domain.question;

import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@NoArgsConstructor
public class Question {

    private ObjectId id;

    @NonNull
    private String title;

    @NonNull
    private QuestionType type;

    @NonNull
    private String helpText;

    @NonNull
    private String placeholder;

    @NonNull
    private boolean hasOtherOption;

    @NonNull
    private boolean isRequired;

    @NonNull
    private List<String> options;


    public String getId() {
        return id.toHexString();
    }
}
