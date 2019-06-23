package itmo.foodtech.moneymaker.dto.response;

import itmo.foodtech.moneymaker.domain.questionResponse.QuestionResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullResponseDto extends ResponseDto {
    @NonNull
    private List<QuestionResponse> replies;
}
