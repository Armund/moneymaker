package itmo.foodtech.moneymaker.domain.dto;

import itmo.foodtech.moneymaker.domain.dto.meta.ExtendedSurveyMeta;
import itmo.foodtech.moneymaker.domain.question.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullSurveyDto extends SurveyDto {

    @NonNull
    private ExtendedSurveyMeta meta;

    @NonNull
    private List<Question> questions;
}
