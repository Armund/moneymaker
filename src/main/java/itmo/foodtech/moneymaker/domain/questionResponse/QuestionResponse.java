package itmo.foodtech.moneymaker.domain.questionResponse;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import itmo.foodtech.moneymaker.domain.question.QuestionType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Transient;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextareaQuestionResponse.class, name = "TEXTAREA"),
        @JsonSubTypes.Type(value = MultipleChoiceQuestionResponse.class, name = "MULTIPLE_CHOICE"),
        @JsonSubTypes.Type(value = CheckboxQuestionResponse.class, name = "CHECKBOX"),
        @JsonSubTypes.Type(value = DropdownQuestionResponse.class, name = "DROPDOWN")
})
@Data
@NoArgsConstructor
public class QuestionResponse {
    @NonNull
    private ObjectId questionId;

    @Transient
    private QuestionType type;
}
