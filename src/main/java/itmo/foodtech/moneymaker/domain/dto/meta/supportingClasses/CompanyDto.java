package itmo.foodtech.moneymaker.domain.dto.meta.supportingClasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

    private String companyId;

    private String companyName;
}
