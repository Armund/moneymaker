package itmo.foodtech.moneymaker.dto.survey;

import itmo.foodtech.moneymaker.dto.meta.BaseSurveyMeta;
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
