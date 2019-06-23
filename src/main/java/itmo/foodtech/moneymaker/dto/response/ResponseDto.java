package itmo.foodtech.moneymaker.dto.response;

import itmo.foodtech.moneymaker.dto.meta.ResponseMeta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    @NonNull
    private String id;

    @NonNull
    private ResponseMeta meta;
}
