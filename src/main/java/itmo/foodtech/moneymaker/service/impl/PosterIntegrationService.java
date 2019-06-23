package itmo.foodtech.moneymaker.service.impl;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import itmo.foodtech.moneymaker.dto.meta.supportingClasses.IntegrationDto;
import itmo.foodtech.moneymaker.dto.meta.supportingClasses.OrderItem;
import itmo.foodtech.moneymaker.service.IntegrationService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class PosterIntegrationService implements IntegrationService {

    private final String accessToken = "430671:0616508460c6523122e380412cc56964"; //we need to put it in some config file

    /**
     * Poster Web Api getTransactionProducts URL
     */
    private String getTransactionURL(String checkId) {
        return "https://joinposter.com/api/dash.getTransactionProducts"
                + "?token=" + accessToken
                + "&transaction_id=" + checkId;
    }

    /**
     * Required to avoid Poster bug with price type
     * Poster, fix it pls!
     */
    private Double posterBugFix(String value) {
        StringBuilder result = new StringBuilder(value);
        result.insert(value.length() - 2, '.');

        return Double.parseDouble(result.toString());
    }

    /**
     * Returns full order price
     */
    private Double orderSum(List<OrderItem> orderItems) {
        Double orderSum = 0.0;
        for(OrderItem item : orderItems) {
            orderSum += item.getPrice();
        }
        return orderSum;
    }

    /**
     * Returns list of ordered products
     */
    private List<OrderItem> orderItems(JsonParser jsonParser) throws IOException {
        List<OrderItem> orderItems = new ArrayList<>();
        String productName = null;
        String price;

        while (!jsonParser.isClosed()) {
            JsonToken jsonToken = jsonParser.nextToken();

            if (JsonToken.FIELD_NAME.equals(jsonToken)) {
                String fieldName = jsonParser.getCurrentName();

                jsonParser.nextToken();

                if ("product_name".equals(fieldName)) {
                    productName = jsonParser.getValueAsString();
                }
                else if ("product_sum".equals(fieldName)) {
                    price = jsonParser.getValueAsString();
                    orderItems.add(new OrderItem(productName, posterBugFix(price)));
                }
            }
        }
        return orderItems;
    }

    @Override
    public IntegrationDto integrationDto(String checkId) {
        if (checkId == null) {
            return null;
        }
        try (JsonParser jsonParser =
                     new JsonFactory().createParser(new URL(getTransactionURL(checkId)))) {

            // Check for poster response errors
            jsonParser.nextFieldName();
            jsonParser.nextFieldName();
            if ("error".equals(jsonParser.getCurrentName())) {
                return null;
            }

            List<OrderItem> orderItems = orderItems(jsonParser);
            Double price = orderSum(orderItems);

            return new IntegrationDto(checkId, price, orderItems);

        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
