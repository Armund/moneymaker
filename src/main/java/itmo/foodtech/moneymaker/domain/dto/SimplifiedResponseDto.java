package itmo.foodtech.moneymaker.domain.dto;

import itmo.foodtech.moneymaker.domain.dto.meta.ResponseMeta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimplifiedResponseDto {

    private String id;

    private ResponseMeta meta;
}
