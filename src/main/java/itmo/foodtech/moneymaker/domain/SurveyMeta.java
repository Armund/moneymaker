package itmo.foodtech.moneymaker.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class SurveyMeta {
    @NonNull
    private String title;

    @NonNull
    private String description;

    private String companyId;

    @NonNull
    private String editorId;

    private String confirmationMessage;

    private int questionsNumber;
}
