package itmo.foodtech.moneymaker.domain;

import lombok.Data;

import java.util.List;

@Data
public class Question {

    private QuestionType type;

    private String label;

    private String placeholder;

    private boolean isRequired;

    private List<String> options;
}
