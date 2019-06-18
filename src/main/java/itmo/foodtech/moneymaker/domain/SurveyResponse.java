package itmo.foodtech.moneymaker.domain;

import itmo.foodtech.moneymaker.domain.question.QuestionReply;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "surveyResponse")
@Data
@NoArgsConstructor
public class SurveyResponse {

    @Id
    @Setter
    private ObjectId id;

    @NonNull
    private ObjectId surveyId;

    @NonNull
    private List<QuestionReply> replies;

    public String getId() {
        return id.toHexString();
    }

}
