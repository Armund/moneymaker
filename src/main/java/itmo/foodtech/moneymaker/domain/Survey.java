package itmo.foodtech.moneymaker.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "survey")
@Data
@NoArgsConstructor
public class Survey {
    @Id
    @Setter
    private ObjectId id;

    @NonNull
    private String surveyTitle;

    @NonNull
    private String surveyDescription;

//    @NonNull
//    private List<Question> questions;


    public String getId() {
        return id.toHexString();
    }
}
