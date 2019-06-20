package itmo.foodtech.moneymaker.domain.dto;

import itmo.foodtech.moneymaker.domain.dto.meta.ResponseMeta;
import itmo.foodtech.moneymaker.domain.questionResponse.QuestionResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullResponseDto {

    @NonNull
    private String id;

    @NonNull
    private ResponseMeta meta;

    @NonNull
    private List<QuestionResponse> replies;
}
