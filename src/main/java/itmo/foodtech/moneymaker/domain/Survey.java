package itmo.foodtech.moneymaker.domain;

import itmo.foodtech.moneymaker.domain.question.Question;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "survey")
@Data
@NoArgsConstructor
public class Survey {
    @Id
    @Setter
    private ObjectId id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private String companyId;

    @NonNull
    private List<String> editorEmails;

    @NonNull
    private String confirmationMessage;

    @NonNull
    private List<Question> questions;

    public String getId() {
        return id.toHexString();
    }
}
