package itmo.foodtech.moneymaker.domain.question.questionSubtypes;

import itmo.foodtech.moneymaker.domain.question.Question;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
public class DropdownQuestion extends Question {

    @NonNull
    private List<String> options;
}
