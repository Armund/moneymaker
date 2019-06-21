package itmo.foodtech.moneymaker.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullSurveyResults extends SurvayResults{

    @NonNull
    private FullSurveyDto survey;

    List<FullResponseDto> responses;
}
