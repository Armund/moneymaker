package itmo.foodtech.moneymaker.domain.question;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextareaQuestion.class, name = "TEXTAREA"),
        @JsonSubTypes.Type(value = MultipleChoiceQuestion.class, name = "MULTIPLE_CHOICE"),
        @JsonSubTypes.Type(value = CheckboxQuestion.class, name = "CHECKBOX"),
        @JsonSubTypes.Type(value = DropdownQuestion.class, name = "DROPDOWN")
})
@Data
@NoArgsConstructor
public class Question {

    private ObjectId id;

    @NonNull
    private String title;

    @NonNull
    private QuestionType type;

    private String helpText;

    private String placeholder;

    private boolean isRequired;

    public String getId() {
        return id.toHexString();
    }
}
