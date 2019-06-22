package itmo.foodtech.moneymaker.domain.question;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import itmo.foodtech.moneymaker.domain.question.questionSubtypes.CheckboxQuestion;
import itmo.foodtech.moneymaker.domain.question.questionSubtypes.DropdownQuestion;
import itmo.foodtech.moneymaker.domain.question.questionSubtypes.MultipleChoiceQuestion;
import itmo.foodtech.moneymaker.domain.question.questionSubtypes.TextareaQuestion;
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
        @JsonSubTypes.Type(value = DropdownQuestion.class, name = "DROPDOWN")
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

    /*public String getId() {
        return id.toHexString();
    }*/

    @JsonDeserialize(using = QuestionTypeDeserializer.class)
    public enum QuestionType {
        DROPDOWN,
        TEXTAREA,
        CHECKBOX,
        MULTIPLE_CHOICE,
        TEXT
    }
}
