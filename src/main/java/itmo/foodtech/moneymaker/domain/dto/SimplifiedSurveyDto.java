package itmo.foodtech.moneymaker.domain.dto;

import itmo.foodtech.moneymaker.domain.dto.meta.BaseSurveyMeta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimplifiedSurveyDto extends SurveyDto {

    @NonNull
    private BaseSurveyMeta meta;
}