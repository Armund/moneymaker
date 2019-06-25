package itmo.foodtech.moneymaker.dto.survey.finalDto;

import itmo.foodtech.moneymaker.dto.response.FullResponseDto;
import itmo.foodtech.moneymaker.dto.survey.FullSurveyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResult {

    @NonNull
    private FullSurveyDto survey;

    private FullResponseDto response;
}
