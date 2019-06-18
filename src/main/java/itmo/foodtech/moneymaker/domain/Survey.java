package itmo.foodtech.moneymaker.domain;

import itmo.foodtech.moneymaker.domain.question.Question;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "survey")
@Data
@NoArgsConstructor
public class Survey {
    @Id
    private ObjectId id;

    @NonNull
    private String title;

    private String description;

    private String companyId;

    @NonNull
    private List<String> editorEmails;

    private String confirmationMessage;

    @NonNull
    private List<Question> questions;

    public String getId() {
        return id.toHexString();
    }
}
