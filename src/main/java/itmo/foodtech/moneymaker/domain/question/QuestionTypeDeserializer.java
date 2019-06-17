package itmo.foodtech.moneymaker.domain.question;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

class QuestionTypeDeserializer extends JsonDeserializer<QuestionType> {

    @Override
    public QuestionType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        if (node == null) {
            return null;
        }

        String text = node.textValue();

        if (text == null) {
            return null;
        }

        return QuestionType.valueOf(text.toUpperCase());
    }
}
