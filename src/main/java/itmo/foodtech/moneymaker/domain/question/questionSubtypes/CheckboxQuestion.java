package itmo.foodtech.moneymaker.domain.question.questionSubtypes;

import itmo.foodtech.moneymaker.domain.question.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckboxQuestion extends Question {

    //private boolean hasOtherOption;

    @NonNull
    private List<String> options;
}
