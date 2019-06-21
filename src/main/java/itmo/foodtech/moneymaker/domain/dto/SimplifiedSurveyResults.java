package itmo.foodtech.moneymaker.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimplifiedSurveyResults extends SurvayResults {
    @NonNull
    SimplifiedSurveyDto survey;

    List<SimplifiedResponseDto> responses;
}
