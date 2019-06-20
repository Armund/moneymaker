package itmo.foodtech.moneymaker.domain.dto.meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtendedSurveyMeta extends BaseSurveyMeta {

    private String confirmationMessage;
}
