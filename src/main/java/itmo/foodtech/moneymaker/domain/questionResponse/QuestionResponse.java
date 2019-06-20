package itmo.foodtech.moneymaker.domain.questionResponse;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import itmo.foodtech.moneymaker.domain.question.Question;
import itmo.foodtech.moneymaker.domain.questionResponse.responseSubtypes.*;
import lombok.AllArgsConstructor;
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
        @JsonSubTypes.Type(value = DropdownQuestionResponse.class, name = "DROPDOWN"),
        @JsonSubTypes.Type(value = TextQuestionResponse.class, name = "TEXT")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {
    @NonNull
    private String questionId;

    @Transient
    private Question.QuestionType type;
}
