package itmo.foodtech.moneymaker.domain.question;

import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@NoArgsConstructor
public class Question {

    @Setter
    private ObjectId id;

    @NonNull
    private QuestionType type;

    @NonNull
    private String label;

    @NonNull
    private String placeholder;

    @NonNull
    private boolean isRequired;

    @NonNull
    private List<String> options;


    public String getId() {
        return id.toHexString();
    }
}
