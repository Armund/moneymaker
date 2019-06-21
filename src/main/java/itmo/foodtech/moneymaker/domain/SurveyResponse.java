package itmo.foodtech.moneymaker.domain;

import itmo.foodtech.moneymaker.domain.questionResponse.QuestionResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "surveyResponse")
@Data
@NoArgsConstructor
public class SurveyResponse {

    @Id
    private String id;

    @NonNull
    private String surveyId;

    private String checkId;

    private int answeredQuestionsNumber;

    @NonNull
    private List<QuestionResponse> replies;

    /*public String getId() {
        return id.toHexString();
    }*/

}
