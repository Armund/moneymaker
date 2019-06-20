package itmo.foodtech.moneymaker.domain.question;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = QuestionTypeDeserializer.class)
public enum QuestionType {
    DROPDOWN,
    TEXTAREA,
    CHECKBOX,
    MULTIPLE_CHOICE,
    TEXT
}
