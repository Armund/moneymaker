package itmo.foodtech.moneymaker.domain.dto.meta.supportingClasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    private String item;

    private Double price;
}
