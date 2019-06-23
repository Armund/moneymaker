package itmo.foodtech.moneymaker.dto.meta;

import itmo.foodtech.moneymaker.dto.meta.supportingClasses.IntegrationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMeta {

    private IntegrationDto integrationInfo;

    private int answeredQuestionsNumber;
}
