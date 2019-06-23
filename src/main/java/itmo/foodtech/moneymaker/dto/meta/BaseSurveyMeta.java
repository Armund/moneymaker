package itmo.foodtech.moneymaker.dto.meta;

import itmo.foodtech.moneymaker.dto.meta.supportingClasses.CompanyDto;
import itmo.foodtech.moneymaker.dto.meta.supportingClasses.EditorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseSurveyMeta {
    @NonNull
    private String title;

    @NonNull
    private String description;

    private CompanyDto company;

    private EditorDto editor;

    private int questionsNumber;
}
