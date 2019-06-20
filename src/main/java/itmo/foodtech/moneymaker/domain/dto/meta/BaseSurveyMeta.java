package itmo.foodtech.moneymaker.domain.dto.meta;

import itmo.foodtech.moneymaker.domain.dto.meta.supportingClasses.CompanyDto;
import itmo.foodtech.moneymaker.domain.dto.meta.supportingClasses.EditorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseSurveyMeta {
    @NonNull
    private String title;

    @NonNull
    private String description;

    private CompanyDto company;

    private List<EditorDto> editors;

    private int questionsNumber;
}
