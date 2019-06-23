package itmo.foodtech.moneymaker.dto.meta.supportingClasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "integrationData")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntegrationDto {

    @Id
    private String checkId;

    private Double orderPrice;

    private List<OrderItem> orderItems;
}
