package itmo.foodtech.moneymaker.domain.dto.meta.supportingClasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntegrationDto {

    private String checkId;

    private Double orderPrice;

    private List<OrderItem> orderItems;
}
