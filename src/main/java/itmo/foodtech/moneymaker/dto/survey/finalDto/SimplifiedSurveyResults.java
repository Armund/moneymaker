package itmo.foodtech.moneymaker.dto.survey.finalDto;

import itmo.foodtech.moneymaker.dto.response.SimplifiedResponseDto;
import itmo.foodtech.moneymaker.dto.survey.SimplifiedSurveyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimplifiedSurveyResults extends SurveyResults {
    @NonNull
    SimplifiedSurveyDto survey;

    List<SimplifiedResponseDto> responses;
}
