package itmo.foodtech.moneymaker.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
public class SurveyMeta {
    @NonNull
    private String title;

    @NonNull
    private String description;

    private String companyId;

    private List<String> editorsId;

    private String confirmationMessage;

    private int questionsNumber;
}
