package itmo.foodtech.moneymaker.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResult {

    @NonNull
    FullSurveyDto survey;

    FullResponseDto response;
}
