package itmo.foodtech.moneymaker.domain.question;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import itmo.foodtech.moneymaker.domain.question.questionSubtypes.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextareaQuestion.class, name = "TEXTAREA"),
        @JsonSubTypes.Type(value = MultipleChoiceQuestion.class, name = "MULTIPLE_CHOICE"),
        @JsonSubTypes.Type(value = CheckboxQuestion.class, name = "CHECKBOX"),
        @JsonSubTypes.Type(value = DropdownQuestion.class, name = "DROPDOWN"),
        @JsonSubTypes.Type(value = TextQuestion.class, name = "TEXT")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    private String id;

    @NonNull
    private String title;

    @NonNull
    private QuestionType type;

    private String helpText;

    private String placeholder;

    private boolean isRequired;

    public enum QuestionType {
        DROPDOWN,
        TEXTAREA,
        CHECKBOX,
        MULTIPLE_CHOICE,
        TEXT
    }
}
