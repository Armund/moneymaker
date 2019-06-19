package itmo.foodtech.moneymaker.domain.question;

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
