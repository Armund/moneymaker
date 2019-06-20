package itmo.foodtech.moneymaker.domain.dto;

import itmo.foodtech.moneymaker.domain.dto.meta.BaseSurveyMeta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimplifiedSurveyDto {

    private String id;

    private BaseSurveyMeta meta;
}
