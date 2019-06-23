package itmo.foodtech.moneymaker.dto.survey.finalDto;

import itmo.foodtech.moneymaker.dto.response.FullResponseDto;
import itmo.foodtech.moneymaker.dto.survey.FullSurveyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullSurveyResults extends SurveyResults {

    @NonNull
    private FullSurveyDto survey;

    private List<FullResponseDto> responses;
}
