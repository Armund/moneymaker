package itmo.foodtech.moneymaker.domain.dto;

import itmo.foodtech.moneymaker.domain.dto.meta.ExtendedSurveyMeta;
import itmo.foodtech.moneymaker.domain.question.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullSurveyDto {

    @Id
    private String id;

    private ExtendedSurveyMeta meta;

    private List<Question> questions;
}
