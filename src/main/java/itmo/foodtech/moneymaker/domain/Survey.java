package itmo.foodtech.moneymaker.domain;

import itmo.foodtech.moneymaker.domain.question.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "survey")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Survey {
    @Id
    private String id;

    @NonNull
    private SurveyMeta meta;

    @NonNull
    private List<Question> questions;
}
